package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

public class FieldV1 {
    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("===== fields() =====");
        Field[] fields = helloClass.getFields(); // public 필드만 가져옴
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        System.out.println("===== declaredFields() =====");
        Field[] declaredFields = helloClass.getDeclaredFields(); // 모든 필드 가져옴
        for (Field field : declaredFields) {
            System.out.println("declaredField = " + field);
        }
    }
}
