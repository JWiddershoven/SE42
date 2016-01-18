/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptiewachtwoorden;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * FXML Controller class
 *
 * @author Jelle
 */
public class EncryptieWindowController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private CheckBox cbEncrypt;

    @FXML
    private PasswordField pfWachtwoord;

    @FXML
    private TextArea taBericht;

    int count = 1000;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void go() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException, NoSuchProviderException, ClassNotFoundException {
        if (cbEncrypt.isSelected()) {
            PBEKeySpec pbeKeySpec;
            PBEParameterSpec pbeParamSpec;
            SecretKeyFactory keyFac;
            final Random r = new SecureRandom();
            byte[] salt = new byte[8];

            r.nextBytes(salt);

            pbeParamSpec = new PBEParameterSpec(salt, count);
            char[] password = pfWachtwoord.getText().toCharArray();
            pbeKeySpec = new PBEKeySpec(password);
            Arrays.fill(password, '0');

            keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
            byte[] input = taBericht.getText().getBytes("UTF-8");
            byte[] encryptedMessage = pbeCipher.doFinal(input);

            System.out.println("Salt: " + Arrays.toString(salt));
            System.out.println("Message: " + new String(input));
            System.out.println("Encrypted message: " + Arrays.toString(encryptedMessage));

            FileOutputStream fos = new FileOutputStream("encrypted-message");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(salt);
                oos.writeObject(encryptedMessage);
            }
            catch (IOException ex) {
                Logger.getLogger(EncryptieWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                fos.close();
            }

            System.out.println("Encrypted-message is opgeslagen!");
        }
        else {
            FileInputStream fis = new FileInputStream("encrypted-message");
            ObjectInputStream ois = new ObjectInputStream(fis);
            byte[] salt = (byte[]) ois.readObject();
            byte[] encryptedMessage = (byte[]) ois.readObject();

            PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, count);
            char[] password = pfWachtwoord.getText().toCharArray();
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
            Arrays.fill(password, '0');

            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

            Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
            
            byte[] message = pbeCipher.doFinal(encryptedMessage);
            taBericht.clear();
            taBericht.setText(new String(message, "UTF-8"));
        }
    }
}
