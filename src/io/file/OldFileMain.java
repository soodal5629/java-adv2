package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 파일 다루는 예전 방식
 */
public class OldFileMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("File exists: " + file.exists());

        // 파일이 이미 존재하면 false
        boolean created = file.createNewFile();
        System.out.println("File created: " + created);

        boolean dirCreated = directory.mkdir();
        System.out.println("Directory created: " + dirCreated);

        //boolean deleted = file.delete();
        //System.out.println("File deleted: " + deleted);

        // boolean dirDeleted = directory.delete();
        //System.out.println("Directory deleted: " + dirDeleted);

        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Directory: " + directory.isDirectory());
        System.out.println("File Name: " + file.getName());
        System.out.println("File Size: " + file.length() + " bytes");

        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile);
        System.out.println("File renamed: " + renamed);
        long lastModified = newFile.lastModified();
        System.out.println("Last modified: " + new Date(lastModified));
    }
}
