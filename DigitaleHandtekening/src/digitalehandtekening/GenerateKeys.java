/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalehandtekening;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jelle
 */
public class GenerateKeys {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Generating keys...");
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = new SecureRandom();
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
            
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("public.p12")))
            {
                out.writeObject(pair.getPublic());
            }
            
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("private.p12")))
            {
                out.writeObject(pair.getPrivate());
            }
            
            System.out.println(pair.getPublic());
            System.out.println(pair.getPrivate());
        }
        catch (IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
