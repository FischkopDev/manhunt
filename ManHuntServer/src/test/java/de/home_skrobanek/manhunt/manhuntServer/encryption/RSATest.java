package de.home_skrobanek.manhunt.manhuntServer.encryption;

import org.junit.jupiter.api.Test;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class RSATest {

    @Test
    public void RSAInit() {
        try {
            RSAKeyPairGenerator keys = new RSAKeyPairGenerator();

            assertNotNull(keys.getPrivateKey());
            assertNotNull(keys.getPublicKey());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void RSAGetKeys(){
        RSAUtil rsa = new RSAUtil();

        assertNotNull(rsa.getPublicKey());
        assertNotNull(rsa.getPrivateKey());
    }

    @Test
    public void RSAencryption(){
        String content = "test";
        RSAUtil rsa = new RSAUtil();

        try {
            assertFalse(content.equals(rsa.encrypt(content).toString()));

        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void RSAdecryption(){
        String content = "test";
        RSAUtil rsa = new RSAUtil();

        try {
            String cipher = rsa.encrypt(content);

            assertTrue(content.equals(rsa.decrypt(cipher)));
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
