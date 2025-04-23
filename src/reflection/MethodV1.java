package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;
        System.out.println("===== methods() =====");
        Method[] methods = helloClass.getMethods(); // 상속받은 메소드 포함 public 메소드만 가져옴
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("===== declaredMethods() =====");
        Method[] declaredMethods = helloClass.getDeclaredMethods(); // 상속 제외 자신이 선언한 메소드 모두 가져옴
        for (Method method : declaredMethods) {
            System.out.println("declaredMethod = " + method);
        }
    }
}
