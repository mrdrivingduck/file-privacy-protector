/**
 * @author Mr Dk.
 * @version 2019-05-12
 * 
 * To pad or cut a key if it is not in proper length
 */

package iot.zjt.protector.util;

import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;

public class KeyPadder {

    public static String pad(String origin, SymAlgs algs) {

        StringBuilder sBuilder = new StringBuilder(origin);

        if (algs == SymAlgs.AES) {
            if (sBuilder.length() < 16) {
                while (sBuilder.length() < 16) {
                    sBuilder.append('0');
                }
            } else if (sBuilder.length() < 24) {
                while (sBuilder.length() < 24) {
                    sBuilder.append('0');
                }
            } else if (sBuilder.length() < 32) {
                while (sBuilder.length() < 32) {
                    sBuilder.append('0');
                }
            } else {
                sBuilder = new StringBuilder(sBuilder.substring(0, 32));
            }
        } else if (algs == SymAlgs.DES) {
            if (sBuilder.length() < 8) {
                while (sBuilder.length() < 8) {
                    sBuilder.append('0');
                }
            } else {
                sBuilder = new StringBuilder(sBuilder.substring(0, 8));
            }
        } else if (algs == SymAlgs.DESede) {
            if (sBuilder.length() < 24) {
                while (sBuilder.length() < 24) {
                    sBuilder.append('0');
                }
            } else {
                sBuilder = new StringBuilder(sBuilder.substring(0, 24));
            }
        }
        return sBuilder.toString();
    }
}