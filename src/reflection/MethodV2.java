package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 정적 메서드 호출 - 일반적인 메서드 호출
        BasicData helloInstance = new BasicData();
        helloInstance.call(); // 이 부분은 코드를 변경하지 않는 이상 정적이다.

        // 동적 메서드 호출 - 리플렉션 사용
        Class<? extends BasicData> helloClass = helloInstance.getClass();
        String methodName = "hello";

        // 메서드 이름을 변수로 변경할 수 있다. 두번제 파라미터는 해당 메소드의 파라미터 타입
        Method method1 = helloClass.getDeclaredMethod(methodName, String.class);
        // 첫번째 파라미터는 어떤 인스턴스의 메소드를 호출할 것인지 인스턴스 지정
        // 두번째 파라미터는 호출할 메서드의 파라미터
        Object returnValue = method1.invoke(helloInstance, "hi");
        System.out.println("returnValue = " + returnValue);
    }
}
