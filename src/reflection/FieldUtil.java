package reflection;

import java.lang.reflect.Field;

public class FieldUtil {

    /**
     * 리플렉션으로 모든 객체를 받아서 null일 때 기본값으로 세팅 가능(공통적으로 처리 가능)
     */
    public static void nullFieldToDefault(Object target) throws IllegalAccessException {
        Class<?> targetClass = target.getClass();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.get(target) != null) { // 해당 필드가 null이 아니라는 의미
                continue;
            }
            // 여기서부터는 해당 필드가 null 이라는 의미 - 데이터 타입에 따라 기본값 세팅
            if (field.getType() == String.class) {
                field.set(target, "");
            } else if (field.getType() == Integer.class) {
                field.set(target, 0);
            }
        }
    }
}
