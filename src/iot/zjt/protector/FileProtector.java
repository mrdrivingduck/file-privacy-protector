/**
 * @author Mr Dk.
 * @version 2019-06-02
 * 
 * Main class for encrypt/decrypt files in a directory
 */

package iot.zjt.protector;

import java.io.File;
import java.util.Scanner;

import iot.zjt.encrypt.machine.SymEncrpMachine.SymAlgs;
import iot.zjt.protector.filter.DiaryDirFilter;
import iot.zjt.protector.filter.DiaryFileFilter;
import iot.zjt.protector.util.Crypto;

public class FileProtector {

    /**
     * args[0] - mode - "encrypt"/"decrypt"
     * args[1] - working directory
     */
    public static void main(String[] args) throws Exception {

        boolean isEncrypt = (args[0].equals("encrypt")) ? true : false;
        String directory = args[1];
        // String password = args[2];

        // Password for generating key
        System.out.println("Pls enter your password:");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        scanner.close();
        // Console console = System.console();
        // String password = new String(console.readPassword());

        File dir = new File(directory);
        if (dir.isDirectory()) {
            System.out.println("Working in '" + dir.getName() + "':");
            DiaryDirFilter diaryDirFilter = new DiaryDirFilter();
            DiaryFileFilter diaryFileFilter = new DiaryFileFilter();

            File[] diaryDirs = dir.listFiles(diaryDirFilter);
            int workCounter = 0;
            for (int i = 0; i < diaryDirs.length; i++) {
                System.out.println("In directory '" + diaryDirs[i].getName() + "' ...");
                File[] diaryFiles = diaryDirs[i].listFiles(diaryFileFilter);
                workCounter += diaryFiles.length;
                for (int j = 0; j < diaryFiles.length; j++) {
                    if (isEncrypt) {
                        System.out.println("Encrypting '" + diaryFiles[j].getName() + "' ...");
                        Crypto.encryptFile(diaryFiles[j], password, SymAlgs.AES);
                        System.out.println("Encryption done.");
                    } else {
                        System.out.println("Decrypting '" + diaryFiles[j].getName() + "' ...");
                        Crypto.decryptFile(diaryFiles[j], password, SymAlgs.AES);
                        System.out.println("Decryption done.");
                    }
                }
                System.out.println("Done in directory '" + diaryDirs[i].getName() + "' ...");
            }
            System.out.println("Working on: " + workCounter + " jobs, done.");
        }
    }

}