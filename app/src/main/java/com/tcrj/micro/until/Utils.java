package com.tcrj.micro.until;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by leict on 2019/4/26.
 */

public class Utils {
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer,Base64.DEFAULT);
    }
}
