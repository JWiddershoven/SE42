/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalehandtekening;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
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
            else {
                System.out.println("Invalid file. Filename must contain 'private'.");
                return;
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
        
        FileInputStream fis = new FileInputStream(privateKey);
        ObjectInputStream ois = new ObjectInputStream(fis);
        PrivateKey pKey = null;
        try {
            pKey = (PrivateKey) ois.readObject();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(SignFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ois.close();
        
        Signature sig = Signature.getInstance("SHA1withRSA");
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(pKey.getEncoded());
        RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
        
        sig.initSign(privKey);
        
        FileInputStream fis2 = new FileInputStream(inputEXT);
        DataInputStream dis2 = new DataInputStream(fis2);
        byte[] inputEXTBytes = new byte[(int) inputEXT.length()];
        dis2.readFully(inputEXTBytes);
        dis2.close();
        
        sig.update(inputEXTBytes);
        byte[] signature = sig.sign();
        Byte length = (byte) signature.length;
        //int length2 = Byte.toUnsignedInt(length);
        
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(inputEXT.getPath().substring(0, inputEXT.getPath().lastIndexOf(File.separator)) + File.separator + "INPUT(SignedBy" + naam + ").EXT"));
        dos.write(length);
        dos.write(signature);
        dos.write(inputEXTBytes);
    
        dos.close();
    } 
}
