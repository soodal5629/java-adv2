package webservice;

import io.member.impl.FileMemberRepository;
import was.httpserver.HttpServer;
import was.httpserver.ServletManager;
import was.httpserver.serlet.DiscardServlet;
import was.httpserver.serlet.annotation.AnnotationServletV3;

import java.io.IOException;
import java.util.List;

public class MemberServiceMain {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        FileMemberRepository memberRepository = new FileMemberRepository();
        MemberController memberController = new MemberController(memberRepository);
        AnnotationServletV3 servlet = new AnnotationServletV3(List.of(memberController));
        ServletManager servletManager = new ServletManager();
        servletManager.add("/favicon.ico", new DiscardServlet());
        servletManager.setDefaultServlet(servlet);

        HttpServer server = new HttpServer(PORT, servletManager);
        server.start();
    }
}
