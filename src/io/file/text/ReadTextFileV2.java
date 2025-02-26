package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ReadTextFileV2 {
    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "abc\n가나다라";
        System.out.println("=== Write String ===");
        System.out.println(writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, StandardCharsets.UTF_8);
        // 한번에 다 가져와서 파일에서 한줄씩 읽기
        System.out.println("=== Read String ===");
        List<String> lines = Files.readAllLines(path); // default: UTF-8
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i+1) + ": " + lines.get(i));
        }

        // 한 줄씩 나눠서 읽기 즉, 한 줄 단위로 메모리에 올림
        try(Stream<String> lineStream = Files.lines(path)) {
            lineStream.forEach(System.out::println);
        }
    }
}
