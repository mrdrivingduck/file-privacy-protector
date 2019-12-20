/**
 * @author Mr Dk.
 * @version 2019/12/20
 * 
 * For filtering emotions
 */

package iot.zjt.protector.filter;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

public class EmotionFileFilter implements FileFilter {

    private String fileRegex = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))";

    @Override
    public boolean accept(File path) {
        return path.isFile() && Pattern.matches(fileRegex, path.getName());
    }
}