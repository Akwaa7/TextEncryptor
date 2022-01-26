package fr.akwaa.textencryptor.mainframe;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Créé le 21/01/2022 à 18h29 par valen
 **/

public class MainFrame extends JFrame {
    private final DecryptPanel decryptPanel;

    public MainFrame() throws HeadlessException{
        super("TextEncryptor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 270);
        try{
            setIconImage(Toolkit.getDefaultToolkit().createImage(Objects.requireNonNull(getClass().getResourceAsStream("/assets/frame_icon.png")).readAllBytes()));
        }catch(IOException e){
            e.printStackTrace();
        }
        setAlwaysOnTop(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setType(Type.NORMAL);

        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        decryptPanel = new DecryptPanel();
        tabbedPane.addTab("Encrypt Text", new EncryptPanel(this));
        tabbedPane.addTab("Decrypt Text", decryptPanel);
        tabbedPane.addTab("Credits", new CreditsPanel());
        tabbedPane.setSize(150, 125);
        add(tabbedPane);

        setVisible(true);
    }

    public DecryptPanel getDecryptPanel(){
        return decryptPanel;
    }
}