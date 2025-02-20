package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {
    private static final int BUFFER_SIZE = 8192;
    public static void main(String[] args) throws IOException {
        String writeString = "ABCD\n가나다";
        System.out.println("=== Write String ===");
        System.out.println(writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);
        String line;
        // BufferedReader는 한줄 라인 단위로 읽을 수 있음
        while((line = br.readLine()) != null) {
            // StringBuilder에 append할 때는 \n이 사라지기 때문에 따로 붙여줘야 함
            content.append(line).append("\n");
        }

        br.close();
        System.out.println("=== Read String ===");
        // StringBuilder 찍을 때는 알아서 toString()호출해서 찍히기 때문에 toString() 안해도 됨
        System.out.println(content);
    }
}
