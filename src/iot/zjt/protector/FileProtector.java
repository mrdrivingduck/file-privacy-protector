package iot.zjt.protector;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import iot.zjt.encrypt.machine.SymEncrpMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;
import iot.zjt.encrypt.util.CoderUtil;

public class FileProtector {

    private static void encryptFile(File file, String key)
        throws Exception {

        FileInputStream fin = new FileInputStream(file);
        Long fileLength = file.length();
        byte[] plainText = new byte[fileLength.intValue()];
        fin.read(plainText);
        fin.close();

        String normal_key = KeyPadder.pad(key, SymAlgs.AES);
        byte[] cipherText = SymEncrpMachine.encrypt(
            plainText,
            normal_key.getBytes(),
            SymAlgs.AES
        );
        String cipherBase64 = CoderUtil.toBase64(cipherText);

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(cipherBase64.getBytes());
        fout.close();
    }

    private static void decryptFile(File file, String key)
        throws Exception {

        FileInputStream fin = new FileInputStream(file);
        Long fileLength = file.length();
        byte[] cipherBase64 = new byte[fileLength.intValue()];
        fin.read(cipherBase64);
        fin.close();

        byte[] cipherText = CoderUtil.fromBase64(new String(cipherBase64));
        String normal_key = KeyPadder.pad(key, SymAlgs.AES);
        byte[] plainText = SymEncrpMachine.decrypt(
            cipherText,
            normal_key.getBytes(),
            SymAlgs.AES
        );

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(plainText);
        fout.close();
    }

    public static void main(String[] args) throws Exception {

        boolean isEncrypt = (args[0].equals("encrypt")) ? true : false;
        String directory = args[1];

        System.out.println("Pls enter your password:");
        Console console = System.console();
        char[] passwd = console.readPassword();
        String password = new String(passwd);
        // String password = args[2];

        File dir = new File(directory);
        if (dir.isDirectory()) {
            File[] allFiles = dir.listFiles();
            for (int i = 0; i < allFiles.length; i++) {
                if (allFiles[i].isFile() &&
                    allFiles[i].getName().endsWith(".md") &&
                    !allFiles[i].getName().equals("README.md") ){
                    if (isEncrypt) {
                        FileProtector.encryptFile(allFiles[i], password);
                    } else {
                        FileProtector.decryptFile(allFiles[i], password);
                    }
                }
            }
        }
    }
}