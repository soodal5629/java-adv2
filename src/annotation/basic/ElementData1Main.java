package annotation.basic;

import java.util.Arrays;

public class ElementData1Main {
    public static void main(String[] args) {
        Class<ElementData1> annoClass = ElementData1.class;
        // ElementData1 클래스에 선언된 어노테이션을 찾을 수 있음
        AnnoElement annotation = annoClass.getAnnotation(AnnoElement.class);
        String value = annotation.value();
        System.out.println("value = " + value);
        int count = annotation.count();
        System.out.println("count = " + count);
        String[] tags = annotation.tags();
        System.out.println("tags = " + Arrays.toString(tags));

    }
}
