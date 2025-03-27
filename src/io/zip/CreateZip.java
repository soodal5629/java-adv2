package io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZip {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("temp");
        //Path file = Files.createFile(path);
        FileOutputStream fos = new FileOutputStream("temp/test.zip");
        File file = new File("temp/test.txt");

        try(ZipOutputStream zos = new ZipOutputStream(fos)) {
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            zos.closeEntry();
        }
    }
}
