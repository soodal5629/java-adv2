package io.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * 최근 방식으로 파일 경로 표시(Path, Files 사용)
 */
public class NewFilesPath {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("temp/..");
        System.out.println("path = " + path);

        // 절대 경로
        // .. 은 temp의 상위 경로, 즉 java-adv2 폴더를 의미하며 내가 입력한 모든 경로가 출력됨
        System.out.println("Absolute path = " + path.toAbsolutePath()); // C:\Users\chaer\workspace\java-adv2\temp\..

        // 정규 경로
        // .. 과 같이 경로 계산이 모두 끝난 경로 (하나만 존재)
        System.out.println("Canonical path = " + path.toRealPath()); // C:\Users\chaer\workspace\java-adv2

        Stream<Path> pathStream = Files.list(path);
        List<Path> list = pathStream.toList();
        pathStream.close();

        for (Path p : list) {
            System.out.println((Files.isRegularFile(p) ? "F" : "D") + " | " + p.getFileName());
        }
    }
}
