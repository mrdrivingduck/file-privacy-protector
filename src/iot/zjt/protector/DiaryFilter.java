package iot.zjt.protector;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Mr Dk.
 * @version 2019-05-13
 */

public class DiaryFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        if (!pathname.isFile()) {
            return false;
        }
        if (pathname.getName().equals("README.md")) {
            return false;
        }
        if (!pathname.getName().endsWith(".md")) {
            return false;
        }
        return true;
    }
}