/**
 * @author Mr Dk.
 * @version 2019-05-13
 * 
 * Main class for encrypt/decrypt files in a directory
 */

package iot.zjt.protector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import iot.zjt.encrypt.machine.SymEncrpMachine;
import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;
import iot.zjt.encrypt.util.CoderUtil;

public class FileProtector {

    /**
     * args[0] - mode - "encrypt"/"decrypt"
     * args[1] - working directory
     */
    public static void main(String[] args) throws Exception {

        boolean isEncrypt = (args[0].equals("encrypt")) ? true : false;
        String directory = args[1];

        // Password for generating key
        System.out.println("Pls enter your password:");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        scanner.close();
        // Console console = System.console();
        // String password = new String(console.readPassword());

        File dir = new File(directory);
        if (dir.isDirectory()) {
            DiaryFilter diaryFilter = new DiaryFilter();
            File[] allFiles = dir.listFiles(diaryFilter);
            System.out.println("In " + dir.getName() + ":");
            for (int i = 0; i < allFiles.length; i++) {
                if (isEncrypt) {
                    System.out.println(
                        "Encrypting " +
                        allFiles[i].getName() +
                        " ..."
                    );
                    FileProtector.encryptFile(
                        allFiles[i], password, SymAlgs.AES);
                    System.out.println("Encryption done.");
                } else {
                    System.out.println(
                        "Decrypting" +
                        allFiles[i].getName() +
                        " ..."
                    );
                    FileProtector.decryptFile(
                        allFiles[i], password, SymAlgs.AES);
                    System.out.println("Decryption done.");
                }
            }
            System.out.println("Working on: " + allFiles.length + " jobs, done.");
        }
    }

    private static void encryptFile(File file, String key, SymAlgs algs)
        throws Exception {

        FileInputStream fin = new FileInputStream(file);
        Long fileLength = file.length();
        byte[] plainText = new byte[fileLength.intValue()];
        fin.read(plainText);
        fin.close();

        String normal_key = KeyPadder.pad(key, algs);
        byte[] cipherText = SymEncrpMachine.encrypt(
            plainText,
            normal_key.getBytes(),
            algs
        );
        String cipherBase64 = CoderUtil.toBase64(cipherText);

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(cipherBase64.getBytes());
        fout.close();
    }

    private static void decryptFile(File file, String key, SymAlgs algs)
        throws Exception {

        FileInputStream fin = new FileInputStream(file);
        Long fileLength = file.length();
        byte[] cipherBase64 = new byte[fileLength.intValue()];
        fin.read(cipherBase64);
        fin.close();

        byte[] cipherText = CoderUtil.fromBase64(new String(cipherBase64));
        String normal_key = KeyPadder.pad(key, algs);
        byte[] plainText = SymEncrpMachine.decrypt(
            cipherText,
            normal_key.getBytes(),
            algs
        );

        FileOutputStream fout = new FileOutputStream(file);
        fout.write(plainText);
        fout.close();
    }
    
}