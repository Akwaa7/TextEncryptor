package fr.akwaa.textencryptor.mainframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Créé le 24/01/2022 à 18h54 par valen
 **/

public class CreditsPanel extends JPanel {
    public CreditsPanel(){
        super();
        setBorder(new EmptyBorder(new Insets(50, 150, 50, 150)));

        JLabel authorLabel = new JLabel("By Akwaa7");
        add(authorLabel);
        JButton githubButton = new JButton("https://github.com/Akwaa7");
        githubButton.addActionListener(e -> {
            if(!Desktop.isDesktopSupported() || !Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) return;
            try{
                Desktop.getDesktop().browse(new URL("https://github.com/Akwaa7").toURI());
            }catch(URISyntaxException | IOException e1){
                e1.printStackTrace();
            }
        });
        add(githubButton);
    }
}