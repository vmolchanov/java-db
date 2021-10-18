package routes;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import java.util.Map;

public class Router {
    private final ServletContextHandler context;

    private Router(ServletContextHandler context) {
        this.context = context;
    }

    public static Router runRouter(int options, Map<String, HttpServlet> routes) {
        ServletContextHandler context = new ServletContextHandler(options);
        Router router = new Router(context);

        for (Map.Entry<String, HttpServlet> route : routes.entrySet()) {
            router.setRoute(route.getKey(), route.getValue());
        }

        return router;
    }

    void setRoute(final String path, final HttpServlet servlet) {
        context.addServlet(new ServletHolder(servlet), path);
    }

    public ServletContextHandler getContext() {
        return context;
    }
}
