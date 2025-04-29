package annotation.basic;

import util.MyLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {
    String value();
    int count() default 0;
    String [] tags() default {};

    // MyLogger data(); // 사용자 정의 타입 혹은 클래스는 적용 X
    Class<? extends MyLogger> annoData() default MyLogger.class; // 사용자 정의 클래스는 사용할 수 없지만 클래스 정보는 가능
}
