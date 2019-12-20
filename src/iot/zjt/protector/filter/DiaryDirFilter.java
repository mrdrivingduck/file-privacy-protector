/**
 * @author Mr Dk.
 * @version 2019-06-02
 * 
 * For filtering diary directories in order of years
 */

package iot.zjt.protector.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class DiaryDirFilter implements FileFilter {

    private String fileRegex = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})";

    @Override
    public boolean accept(File path) {
        return path.isDirectory() && Pattern.matches(fileRegex, path.getName());
    }
}