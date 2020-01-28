# file-privacy-protector

🤐 Code for file encryption/decryption.

Created by : Mr Dk.

2019.05.13 @Nanjing, Jiangsu, China

---

## About

Programs for encryption/decryption of files in a directory with your password.

## Dependency

Java 8 with JDK & JRE

[encryption-machine](https://github.com/mrdrivingduck/encryption-machine) - A Java wrapper for simple encryption/decryption.

## Usage

Download runnable JAR file from [release](https://github.com/mrdrivingduck/file-privacy-protector/releases).

### Encryption

```bash
$ java -jar ./file-protector.jar encrypt ./
```

Enter the password to generate secret key and encrypt the files filtered by `FileFilter`.

### Decryption

```bash
$ java -jar ./file-protector.jar decrypt ./
```

Enter the password to generate secret key and decrypt the files filtered by `FileFilter`.

## Working Mode

* Encryption/Decryption Algorithm - __AES__
* Cipher text encoding - __base64__
* File filter - All `.md` files except `README.md`

Can be configured in the code.

## License

Copyright © 2019-2020, Jingtang Zhang. ([MIT License](LICENSE))

---

