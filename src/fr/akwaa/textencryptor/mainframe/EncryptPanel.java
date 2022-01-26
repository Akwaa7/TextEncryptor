package fr.akwaa.textencryptor.mainframe;

import fr.akwaa.textencryptor.encryptor.EncryptedFiles;
import fr.akwaa.textencryptor.encryptor.Encryptor;

import javax.swing.*;
import java.awt.*;

/**
 * Créé le 21/01/2022 à 18h50 par valen
 **/

public class EncryptPanel extends JPanel {
    private final JTextField nameTextField;
    private final JTextArea inputTextArea;
    private final JTextField keyTextField;

    public EncryptPanel(MainFrame mainFrame){
        super(new GridBagLayout());

        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(100, 20));
        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        inputScrollPane.setPreferredSize(new Dimension(200, 100));
        keyTextField = new JTextField();
        keyTextField.setPreferredSize(new Dimension(100, 20));
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(e -> {
            if(inputTextArea.getText().isEmpty() || keyTextField.getText().isEmpty()) return;
            if(EncryptedFiles.getEncryptedFilesName().contains(nameTextField.getText())) EncryptedFiles.deleteEncryptedFile(nameTextField.getText());
            EncryptedFiles.createEncryptedFile(nameTextField.getText(), Encryptor.encrypt(inputTextArea.getText(), keyTextField.getText()));
            mainFrame.getDecryptPanel().reloadEncryptedFilesComboBoxItems();
        });
        encryptButton.setPreferredSize(new Dimension(80, 20));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(new JLabel("Name : "), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(nameTextField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(new JLabel("Input : "), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(inputScrollPane, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(new JLabel("Key : "), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        add(keyTextField, gridBagConstraints);
        gridBagConstraints.gridy = 3;
        add(encryptButton, gridBagConstraints);
    }
}
