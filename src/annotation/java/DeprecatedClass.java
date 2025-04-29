package annotation.java;

public class DeprecatedClass {
    public void call1() {
        System.out.println("DeprecatedClass.call1");
    }

    @Deprecated
    public void call2() {
        System.out.println("DeprecatedClass.call2");
    }

    // forRemoval=true: 미래에 언젠가 지울 것이라는 의미 - 강력하게 사용하면 안돼
    @Deprecated(since = "2.4", forRemoval = true)
    public void call3() {
        System.out.println("DeprecatedClass.call3");
    }
}
