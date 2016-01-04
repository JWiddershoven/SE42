/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalehandtekening;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
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
        
        FileInputStream fis = new FileInputStream(privateKey);
        DataInputStream dis = new DataInputStream(fis);
        byte[] privateKeyBytes = new byte[(int) privateKey.length()];
        dis.readFully(privateKeyBytes);
        dis.close();
        
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec((privateKeyBytes));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pKey = kf.generatePrivate(spec);
        
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(pKey);
        sig.update(privateKeyBytes);
        byte[] signature = sig.sign();
        
        System.out.println(signature);
        
        
        
    } 
}
