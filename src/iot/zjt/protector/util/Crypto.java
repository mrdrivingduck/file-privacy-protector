/**
 * @author Mr Dk.
 * @version 2019-06-02
 * 
 * Utility class wrapping encryption machine -
 *      https://github.com/mrdrivingduck/encryption-machine
 */

package iot.zjt.protector.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import iot.zjt.encrypt.machine.SymEncrpMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;
import iot.zjt.encrypt.util.CoderUtil;

public class Crypto {

    public static void encryptFile(File file, String key, SymAlgs algs) throws Exception {

        FileInputStream fin = new FileInputStream(file);
        Long fileLength = file.length();
        byte[] plainText = new byte[fileLength.intValue()];
        fin.read(plainText);
        fin.close();

        String normal_key = KeyPadder.pad(key, algs);
        byte[] cipherText = SymEncrpMachine.encrypt(plainText, normal_key.getBytes(), algs);
        String cipherBase64 = CoderUtil.toBase64(cipherText);

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(cipherBase64.getBytes());
        fout.close();
    }

    public static void decryptFile(File file, String key, SymAlgs algs) throws Exception {

        FileInputStream fin = new FileInputStream(file);
        Long fileLength = file.length();
        byte[] cipherBase64 = new byte[fileLength.intValue()];
        fin.read(cipherBase64);
        fin.close();

        byte[] cipherText = CoderUtil.fromBase64(new String(cipherBase64));
        String normal_key = KeyPadder.pad(key, algs);
        byte[] plainText = SymEncrpMachine.decrypt(cipherText, normal_key.getBytes(), algs);

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(plainText);
        fout.close();
    }
    
}