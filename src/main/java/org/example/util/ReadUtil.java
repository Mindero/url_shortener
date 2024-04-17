package org.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadUtil {
    public static String readLine() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
