package routes;

import servlets.AddUserServlet;
import servlets.ShowUsersServlet;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class Paths {
    public static Map<String, HttpServlet> getRoutesMap() {
        HashMap<String, HttpServlet> routes = new HashMap<>();

        routes.put("/show", new ShowUsersServlet());
        routes.put("/add", new AddUserServlet());

        return routes;
    }
}
