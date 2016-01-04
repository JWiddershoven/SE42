/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalehandtekening;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Jelle
 */
public class SignFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File privateKey = null;
        File inputEXT = null;
        
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File inputFile = fc.getSelectedFile();
            if (inputFile.getName().contains("private"))
            {
                privateKey = inputFile;
                result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION && fc.getSelectedFile().getName().contains("INPUT"))
                {
                    inputEXT = fc.getSelectedFile();
                }
                System.out.println(privateKey.getPath());
                System.out.println(inputEXT.getPath());
            }
        }
        
        System.out.println("Vul je naam in:");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String naam = "";
        try {
            naam = bufferRead.readLine();
        }
        catch (IOException ex) {
            Logger.getLogger(SignFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(naam);
        
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
        }
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
