package iot.zjt.protector;

import java.io.File;

/**
 * @author Mr Dk.
 * @version 2019-05-13
 * 
 * For filtering files that wanted to be encrypted/decrypted
 */

public class FileFilter {

    public static boolean filter(File file) {

        if (!file.isFile()) {
            return false;
        }
        if (file.getName().equals("README.md")) {
            return false;
        }
        if (!file.getName().endsWith(".md")) {
            return false;
        }
        return true;
    }
}