package com.test.inventory.config.jsypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class JsyptImpl implements StringEncryptor {
    private static final String ENCRYPTION_PASSWORD = "TransportMgtSecretKeyEncDecPass";

    @Override
    public String encrypt(String text) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(ENCRYPTION_PASSWORD);
        return encryptor.encrypt(text);
    }

    @Override
    public String decrypt(String encryptedText) {
        BasicTextEncryptor decryptor = new BasicTextEncryptor();
        decryptor.setPassword(ENCRYPTION_PASSWORD);
        return decryptor.decrypt(encryptedText);
    }
}