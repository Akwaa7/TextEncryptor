package fr.akwaa.textencryptor.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Créé le 23/01/2022 à 17h44 par valen
 **/

public class EncryptedFiles {
    public static void createEncryptedFile(String encryotedFileName, String content){
        File encryptedFile = new File("encryptedfiles/" + encryotedFileName + ".encrypted");
        try{
            if(!encryptedFile.exists()){
                if(!encryptedFile.getParentFile().exists() && !encryptedFile.getParentFile().mkdir()) return;
                if(!encryptedFile.createNewFile()) return;

                FileOutputStream fileOutputStream = new FileOutputStream(encryptedFile);
                byte[] contentBytes = content.getBytes();
                fileOutputStream.write(contentBytes);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void deleteEncryptedFile(String encryptedFileName){
        File encryptedFile = new File("encryptedfiles/" + encryptedFileName + ".encrypted");
        if(!encryptedFile.exists()) return;
        if(!encryptedFile.delete()){
            try{
                throw new IOException();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static List<String> getEncryptedFilesName(){
        List<String> encryptedFilesName = new ArrayList<>();
        File encryptedFilesFolder = new File("encryptedfiles/");
        if(!encryptedFilesFolder.exists()) return encryptedFilesName;

        for(File encryptedFile : Objects.requireNonNull(encryptedFilesFolder.listFiles())){
            encryptedFilesName.add(encryptedFile.getName().substring(0, encryptedFile.getName().lastIndexOf(".encrypted")));
        }

        return encryptedFilesName;
    }

    public static String getEncryptedFileContent(String encryptedFileName){
        StringBuilder fileContentBuilder = new StringBuilder();

        try{
            File encryptedFile = new File("encryptedfiles/" + encryptedFileName + ".encrypted");
            if(!encryptedFile.exists()) return "";

            FileInputStream fileInputStream = new FileInputStream(encryptedFile);
            int n;
            while((n = fileInputStream.read()) != -1){
                fileContentBuilder.append((char) n);
            }
            fileInputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return fileContentBuilder.toString();
    }
}