package annotation.basic.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited // 클래스 상속 시 자식도 어노테이션 사용
public @interface InheritedAnnotation {
}
