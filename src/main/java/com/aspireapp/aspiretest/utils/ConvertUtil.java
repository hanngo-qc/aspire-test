package com.aspireapp.aspiretest.utils;

import com.aspireapp.aspiretest.exception.AspireException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConvertUtil {
    private static final Logger LOGGER = Logger.getLogger(ConvertUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private ConvertUtil() {
    }

    public static Map<Object, Object> convertJsonFileToMap(String filePath) {
        LOGGER.info("File path >>>> " + filePath + "\n");

        try {
            return (Map) mapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException var2) {
            throw new AspireException("Failed to convert json file to map >>>> ", var2);
        }
    }
}
