package io.start;

import java.io.IOException;
import java.io.PrintStream;

import static java.nio.charset.StandardCharsets.*;

public class PrintStreamMain {
    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        PrintStream printStream = System.out;
        printStream.println("hihi");

        // 문자를 byte로 변환
        byte[] bytes = "Hello!\n".getBytes(UTF_8);
        // 콘솔에 출력
        printStream.write(bytes); // write는 부모의 기능
        printStream.println("Print!"); // println은 부가로 제공하는 기능
    }
}
