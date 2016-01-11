/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalehandtekening;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Jelle
 */
public class VerifyFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        File publicKey = null;
        File inputEXTSigned = null;
        
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fc.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File inputFile = fc.getSelectedFile();
            if (inputFile.getName().contains("public"))
            {
                publicKey = inputFile;
                result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION && fc.getSelectedFile().getName().contains("INPUT"))
                {
                    inputEXTSigned = fc.getSelectedFile();
                }
                System.out.println(publicKey.getPath());
                System.out.println(inputEXTSigned.getPath());
            }
            else {
                System.out.println("Invalid file. Filename must contain 'private'.");
                return;
            }
        }
        
        FileInputStream fis = new FileInputStream(publicKey);
        ObjectInputStream ois = new ObjectInputStream(fis);
        PublicKey pKey = null;
        try {
            pKey = (PublicKey) ois.readObject();
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(SignFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ois.close();
        fis.close();
        
        FileInputStream fis2 = new FileInputStream(inputEXTSigned);
        DataInputStream dis2 = new DataInputStream(fis2);
        int sigLength = 0;
        
        sigLength = Byte.toUnsignedInt(dis2.readByte());
        
        byte[] signature = new byte[(int) sigLength];
        
        dis2.read(signature, 0, sigLength);
        
        byte[] contents = new byte[(int) inputEXTSigned.length() - sigLength - 1];
        dis2.readFully(contents);
        
        dis2.close();
        fis2.close();
        
        System.out.println(new String(contents, "UTF-8"));
        
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(pKey);
        sig.update(contents);
        boolean verify = sig.verify(signature);
        System.out.println(verify);
        
        if (verify)
        {
            DataOutputStream dis3 = new DataOutputStream(new FileOutputStream(inputEXTSigned.getPath().substring(0, inputEXTSigned.getPath().lastIndexOf(File.separator)) + File.separator + "INPUT(verified).EXT"));
            dis3.write(contents);
            dis3.close();
        }
    }
    
}
