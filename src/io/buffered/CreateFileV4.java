package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

public class CreateFileV4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();
        // 10MB짜리 버퍼를 직접 만들어서 한번에 쓰기
        byte [] buffer = new byte [FILE_SIZE];

        // 10MB 짜리 파일 쓰기
        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[i] = 1;
        }
        fos.write(buffer);
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File Size: " + FILE_SIZE/1024/1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
