package com.aspireapp.aspiretest.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class DataGenerationUtil {
    public static final String GMAIL = "@gmail.com";

    public static String generatePhoneNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String generateGmail(int length) {
        return RandomStringUtils.randomAlphabetic(length) + GMAIL;
    }
}
