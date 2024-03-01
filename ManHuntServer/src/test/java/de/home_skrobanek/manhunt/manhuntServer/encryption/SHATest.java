package de.home_skrobanek.manhunt.manhuntServer.encryption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SHATest {

    @Test
    public void hash(){
        String txt = "HAllo Welt";

        assertFalse(SHAHashing.hashString(txt).equals(txt));
    }
}
