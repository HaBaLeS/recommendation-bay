package net.projektfriedhof.de.habales.datagen.services;

/**
 * Created by falko on 4/10/15.
 */
public interface SecureMessage {

    /**
     * Create sha256 hash of message and return als hex encoded string
     * @param message
     * @return
     */
    String sha265Hex(String message);
}
