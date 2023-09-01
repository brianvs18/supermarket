package com.softlond.model.utils;

import java.util.UUID;

public final class GenerateId {

    private static final String GUION= "-";
    private static final String WITHOUT_SPACE = "";

    private GenerateId() {
    }

    public static String randomId(){
        return UUID.randomUUID().toString().replace(GUION, WITHOUT_SPACE);
    }
}
