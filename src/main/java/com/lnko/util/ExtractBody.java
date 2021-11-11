package com.lnko.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class ExtractBody {

    public static String extractBody(HttpServletRequest request) {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

}
