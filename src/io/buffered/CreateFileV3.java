package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        // 내부에 버퍼 기능을 가지고 있음
        // 버퍼가 가득 차면 FileOutputStream에 버퍼 단위로 보내줌
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);

        long startTime = System.currentTimeMillis();
        // 10MB 짜리 파일 쓰기
        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(1);
        }
        bos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File Size: " + FILE_SIZE/1024/1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
