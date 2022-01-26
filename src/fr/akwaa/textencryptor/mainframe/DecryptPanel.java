package fr.akwaa.textencryptor.mainframe;

import com.sun.tools.javac.Main;
import fr.akwaa.textencryptor.encryptor.EncryptedFiles;
import fr.akwaa.textencryptor.encryptor.Encryptor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Objects;

/**
 * Créé le 21/01/2022 à 18h50 par valen
 **/

public class DecryptPanel extends JPanel {
    private final JComboBox<Object> encryptedFilesComboBox;
    private final JTextField keyTextField;
    private final JTextArea outputTextArea;

    public DecryptPanel(){
        super(new GridBagLayout());

        encryptedFilesComboBox = new JComboBox<>(EncryptedFiles.getEncryptedFilesName().toArray());
        encryptedFilesComboBox.setPreferredSize(new Dimension(100, 20));
        outputTextArea = new JTextArea();
        outputTextArea.setLineWrap(true);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScrollPane.setPreferredSize(new Dimension(200, 100));
        JButton deleteButton = new JButton(new Icon() {
            Icon icon;
            {
                try{
                    icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Objects.requireNonNull(getClass().getResourceAsStream("/assets/delete_icon.png")).readAllBytes()));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void paintIcon(Component component, Graphics graphics, int x, int y){
                Graphics2D graphics2D = (Graphics2D) graphics.create();

                AffineTransform affineTransform = graphics2D.getTransform();

                int scaleX = (int)(x * affineTransform.getScaleX());
                int scaleY = (int)(y * affineTransform.getScaleY());

                int offsetX = (int)(icon.getIconWidth() * (affineTransform.getScaleX() - 1) / 2);
                int offsetY = (int)(icon.getIconHeight() * (affineTransform.getScaleY() - 1) / 2);

                int locationX = scaleX + offsetX;
                int locationY = scaleY + offsetY;

                AffineTransform scaled = AffineTransform.getScaleInstance(1.0 / affineTransform.getScaleX(), 1.0 / affineTransform.getScaleY());
                affineTransform.concatenate(scaled);
                graphics2D.setTransform(affineTransform);

                icon.paintIcon(component, graphics2D, locationX, locationY);

                graphics2D.dispose();
            }

            @Override
            public int getIconWidth(){
                return icon.getIconWidth();
            }

            @Override
            public int getIconHeight(){
                return icon.getIconHeight();
            }
        });
        deleteButton.addActionListener(e -> {
            EncryptedFiles.deleteEncryptedFile((String) encryptedFilesComboBox.getSelectedItem());
            reloadEncryptedFilesComboBoxItems();
        });
        deleteButton.setPreferredSize(new Dimension(20, 20));
        keyTextField = new JTextField();
        keyTextField.setPreferredSize(new Dimension(100, 20));
        JButton uncryptButton = new JButton("Decrypt");
        uncryptButton.addActionListener(e -> outputTextArea.setText(Encryptor.encrypt(EncryptedFiles.getEncryptedFileContent((String) encryptedFilesComboBox.getSelectedItem()), keyTextField.getText())));
        uncryptButton.setPreferredSize(new Dimension(80, 20));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(new JLabel("File : "), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(encryptedFilesComboBox, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        add(deleteButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(new JLabel("Key : "), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(keyTextField, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        add(uncryptButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(new JLabel("Output : "), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(outputScrollPane, gridBagConstraints);
    }

    public void reloadEncryptedFilesComboBoxItems(){
        encryptedFilesComboBox.removeAllItems();
        for(String encryptedFileName : EncryptedFiles.getEncryptedFilesName()){
            encryptedFilesComboBox.addItem(encryptedFileName);
        }
    }
}
