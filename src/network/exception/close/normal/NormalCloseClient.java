package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();
        // 아래의 3개 메소드를 다 호출하면  socket close 중복호출되는데 이미 예방 코드가 다 들어가 있어서 크게 문제되지 않음
        readByInputStream(input, socket);
        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        log("read = " + read);
        if(read == -1) { // 더 이상 읽을 데이터가 없을 때, FIN 패킷 받았을 때 -1 받음
            input.close();
            socket.close(); // 서버에 FIN 패킷 동일하게 보냄
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("readString = " + readString);
        if(readString == null) { // 더 이상 읽을 데이터가 없을 때, FIN 패킷 받았을 때 null 받음
            br.close();
            socket.close(); // 서버에 FIN 패킷 동일하게 보냄
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);
        try {
            dis.readUTF(); // 더 이상 읽을 데이터가 없을 때, FIN 패킷 받았을 때 EOFException 예외 발생
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close();
            socket.close(); // 서버에 FIN 패킷 동일하게 보냄
        }
    }

}
