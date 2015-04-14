package net.projektfriedhof.de.habales.datagen.services.impl;

import jodd.petite.meta.PetiteBean;
import net.projektfriedhof.de.habales.datagen.services.SecureMessage;

import java.security.MessageDigest;

/**
 * Created by falko on 4/10/15.
 */
@PetiteBean(value="secureMessage")
public class SecureMessageImpl implements SecureMessage {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    @Override
    public String sha265Hex(String message) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(message.getBytes());
            byte[] binaryDigest = digest.digest();

            return bytesToHex(binaryDigest);

        } catch (Exception e){
            throw new RuntimeException("Cryptocalypse ... stop all processing now !! ",e );
        }
    }


    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
