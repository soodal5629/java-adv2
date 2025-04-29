package annotation.mapping;

import java.lang.reflect.Method;

public class TestControllerMain {
    public static void main(String[] args) {
        TestController testController = new TestController();
        Class<? extends TestController> aClass = testController.getClass();

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            // 메서드에 붙어있는 어노테이션을 찾아올 수 있음
            SimpleMapping simpleMapping = method.getAnnotation(SimpleMapping.class);
            if(simpleMapping != null) {
                System.out.println("[" + simpleMapping.value() + "] -> " + method);
            }
        }
    }
}
