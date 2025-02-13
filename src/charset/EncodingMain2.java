package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS_949");

    public static void main(String[] args) {
        System.out.println("=== 영문 ASCII 인고딩 ===");
        test("A", US_ASCII, US_ASCII); // ASCII 포함
        test("A", US_ASCII, ISO_8859_1); // ASCII 포함(호환 가능)
        test("A", US_ASCII, EUC_KR); // ASCII 포함(호환 가능)
        test("A", US_ASCII, MS_949); // ASCII 포함(호환 가능)
        test("A", US_ASCII, UTF_8); // ASCII 포함(호환 가능)
        test("A", US_ASCII, UTF_16BE); // 디코딩 실패

        System.out.println("=== 한글 인코딩 - 기본 ===");
        test("가", US_ASCII, US_ASCII); // 인코딩, 디코딩 모두 실패(인코딩이 이상하게 될 뿐 에러는 안남)
        test("가", ISO_8859_1, ISO_8859_1); // 인코딩, 디코딩 모두 실패(인코딩이 이상하게 될 뿐 에러는 안남)
        test("가", EUC_KR, EUC_KR); // 인코딩, 디코딩 모두 성공
        test("가", MS_949, MS_949); // 인코딩, 디코딩 모두 성공
        test("가", UTF_8, UTF_8); // 인코딩, 디코딩 모두 성공
        test("가", UTF_16BE, UTF_16BE); // 인코딩, 디코딩 모두 성공

        System.out.println("=== 한글 인코딩 - 복잡한 문자 ===");
        test("뷁", EUC_KR, EUC_KR); // 인코딩, 디코딩 모두 실패(인코딩이 이상하게 될 뿐 에러는 안남)
        test("뷁", MS_949, MS_949); // 인코딩, 디코딩 가능
        test("뷁", UTF_8, UTF_8); // 인코딩, 디코딩 가능
        test("뷁", UTF_16BE, UTF_16BE); // 인코딩, 디코딩 가능


        System.out.println("=== 한글 인코딩 - 디코딩이 다른 경우 ===");
        test("가", EUC_KR, MS_949); // MS_949가 EUC_KR 확장 버젼이므로 호환 가능
        test("뷃", MS_949, EUC_KR); // 인코딩은 가능, 디코딩 불가능
        test("가", EUC_KR, UTF_8); // 인코딩 가능, 디코딩 불가능 - 호환 불가능
        test("가", MS_949, UTF_8); // 인코딩 가능, 디코딩 불가능 - 호환 불가능
        test("가", UTF_8, MS_949); // 인코딩 가능, 디코딩 불가능 - 호환 불가능

        System.out.println("=== 영문 인코딩 - 디코딩이 다른 경우 ===");
        test("A", EUC_KR, UTF_8); // 호환 가능
        test("A", MS_949, UTF_8); // 호환 가능
        test("A", UTF_8, MS_949); // 호환 가능
        test("A", UTF_8, UTF_16BE); // 호환 불가능
    }

    // 인코딩, 디코딩
    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset);
        String decoded = new String(encoded, decodingCharset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n", text, encodingCharset,
                Arrays.toString(encoded), encoded.length, decodingCharset, decoded);
    }

}
