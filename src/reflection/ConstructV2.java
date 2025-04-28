package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 동적으로 클래스 찾기
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        // String 인자가 있는 생성자 찾기
        Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        // 새로운 객체 생성
        Object instance = constructor.newInstance("hello");
        System.out.println("instance = " + instance);

        // 동적으로 메서드 호출
        Method method1 = aClass.getDeclaredMethod("call");
        method1.invoke(instance);
    }
}
