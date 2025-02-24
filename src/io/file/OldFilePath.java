package io.file;

import java.io.File;
import java.io.IOException;

/**
 * 예전 방식으로 파일 경로 표시(File 사용)
 */
public class OldFilePath {
    public static void main(String[] args) throws IOException {

        File file = new File("temp/..");
        System.out.println("path = " + file.getPath());
        // 절대 경로
        // .. 은 temp의 상위 경로, 즉 java-adv2 폴더를 의미하며 내가 입력한 모든 경로가 출력됨
        System.out.println("Absolute path = " + file.getAbsolutePath()); // C:\Users\chaer\workspace\java-adv2\temp\..

        // 정규 경로
        // .. 과 같이 경로 계산이 모두 끝난 경로 (하나만 존재)
        System.out.println("Canonical path = " + file.getCanonicalPath()); // C:\Users\chaer\workspace\java-adv2

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }

    }
}
