package com.loco.platform.config.utils;

import java.util.UUID;

public final class KeyGenerator {

    public static String generateKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
