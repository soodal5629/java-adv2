package was.httpserver.serlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

public class DiscardServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        // empty (무시)
    }
}
