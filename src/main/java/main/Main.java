package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import routes.Paths;
import routes.Router;

public class Main {
    public static void main(String[] args) throws Exception {
        Router router = Router.runRouter(ServletContextHandler.SESSIONS, Paths.getRoutesMap());

        Server server = new Server(8080);
        server.setHandler(router.getContext());

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
