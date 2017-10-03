package udai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import interactive.Global;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CipherDesu {
    /**
     * generate rsa key pairs
     */
    private static void generateKeyPair() {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(Global.RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e1) {
            if (Global.DEBUG)
                e1.printStackTrace();
            return;
        }
        keyPairGenerator.initialize(Global.RSA_KEYSIZE, secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            oos1 = new ObjectOutputStream(new FileOutputStream(Global.PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(Global.PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
            oos1.close();
            oos2.close();
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return;
        }
    }
    
    /**
     * bytes to hex string
     * @param origBytes
     * @return
     */
    private static String getDigestStr(byte[] origBytes) {
        String tempStr = null;
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < origBytes.length; i++) {
            tempStr = Integer.toHexString(origBytes[i] & 0xff);
            if (tempStr.length() == 1) {
                stb.append("0");
            }
            stb.append(tempStr);
        }
        return stb.toString();
    }
    
    /**
     * initialize AES
     * @param sKey
     * @param cipherMode
     * @return
     */
    private static Cipher initAESCipher(String sKey, int cipherMode) {
        KeyGenerator keyGenerator = null;
        Cipher cipher = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] codeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(codeFormat, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, key);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        return cipher;
    }
    
    /**
     * SHA 256 encrypt
     * @param file
     * @return
     */
    public static String SHA256Encrypt(File file) {
        FileInputStream in = null;
        MessageDigest messagedigest = null;
        try {
            in = new FileInputStream(file);
            messagedigest = MessageDigest.getInstance(Global.SHA_ALGORITHM);
            byte[] buffer = new byte[1024 * 1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                messagedigest.update(buffer, 0, len);
            }
            in.close();
        }
        catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        return getDigestStr(messagedigest.digest());
    }

    /**
     * Encrypt file using AES algorithm
     * @param sourceFile
     * @param fileType
     * @param sKey
     * @return
     */
    public static File encryptAESFile(File sourceFile, String fileType, String sKey) {
        File encrypfile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            Cipher cipher = initAESCipher(sKey, Cipher.ENCRYPT_MODE);
            if (cipher == null)
                return null;
            inputStream = new FileInputStream(sourceFile);
            encrypfile = File.createTempFile(sourceFile.getName(), fileType);
            outputStream = new FileOutputStream(encrypfile);
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = cipherInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            cipherInputStream.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        return encrypfile;
    }

    /**
     * decrypt file using AES algorithm
     * @param sourceFile
     * @param fileType
     * @param sKey
     * @return
     */
    public static File decryptAESFile(File sourceFile, String fileType, String sKey) {
        File decryptFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            decryptFile = File.createTempFile(sourceFile.getName(), fileType);
            Cipher cipher = initAESCipher(sKey, Cipher.DECRYPT_MODE);
            if (cipher == null)
                return null;
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(decryptFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = inputStream.read(buffer)) >= 0) {
                cipherOutputStream.write(buffer, 0, r);
            }
            cipherOutputStream.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        return decryptFile;
    }
    
    /**
     * RSA encrypt
     * @param source
     * @return
     */
    public static String RSAEncrypt(String source) {
        if (!(new File(Global.PUBLIC_KEY_FILE).exists()) && !(new File(Global.PRIVATE_KEY_FILE).exists())) {
            generateKeyPair();
        }
        Key privateKey;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(Global.PRIVATE_KEY_FILE));
            privateKey = (Key) ois.readObject();
            ois.close();
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        Cipher cipher = null;
        BASE64Encoder encoder = null;
        byte[] b = null;
        try {
            cipher = Cipher.getInstance(Global.RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            b = cipher.doFinal(source.getBytes());
            encoder = new BASE64Encoder();
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        return encoder.encode(b);
    }

    /**
     * RSA decrypt
     * @param cryptograph
     * @param PUBLICKEYFILE
     * @return
     */
    public static String RSADecrypt(InputStream cryptograph, String PUBLICKEYFILE) {
        Key publicKey;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PUBLICKEYFILE));
            publicKey = (Key) ois.readObject();
            ois.close();
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        Cipher cipher = null;
        byte[] b = null;
        try {
            cipher = Cipher.getInstance(Global.RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            BASE64Decoder decoder = new BASE64Decoder();
            b = cipher.doFinal(decoder.decodeBuffer(cryptograph));
        } catch (Exception e) {
            if (Global.DEBUG)
                e.printStackTrace();
            return null;
        }
        return new String(b);
    }
}
