package network.tcp.autocloseable;

public class ResourceCloseMainV4 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx = " + throwable);
            }
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {
        try (ResourceV2 resource1 = new ResourceV2("resource1"); ResourceV2 resource2 = new ResourceV2("resource2")) {
            resource1.call();
            resource2.callEx(); // CallException 체크 예외 발생
        } catch(CallException e) { // catch문 사실 필요 없음 그냥 로그 찍으려고 넣음
            System.out.println("ex: " + e);
            throw e;
        }
    }

}
