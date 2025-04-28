package reflection;

import java.lang.reflect.Constructor;

public class ConstructV1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("reflection.data.BasicData");

        System.out.println("===== constructors() =====");
        Constructor<?>[] constructors = aClass.getConstructors(); // public 생성자만 가져옴
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }

        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors(); // 모든 생성자 가져옴
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println("declaredConstructors = " + constructor);
        }
    }
}
