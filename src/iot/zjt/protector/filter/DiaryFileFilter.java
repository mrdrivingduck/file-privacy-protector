/**
 * @author Mr Dk.
 * @version 2019-06-02
 * 
 * For filtering diary files in specific format
 */

package iot.zjt.protector.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class DiaryFileFilter implements FileFilter {

    private String fileRegex = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))[.][m][d]";

    @Override
    public boolean accept(File path) {
        return path.isFile() && Pattern.matches(fileRegex, path.getName());
    }
}