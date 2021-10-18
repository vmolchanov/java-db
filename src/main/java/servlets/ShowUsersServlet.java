package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class ShowUsersServlet extends HttpServlet {
    @Override
    /**
     * http://localhost:8080/show
     */
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        DBService dbService = new DBService();
        Map<String, Object> params = new HashMap<>();
        List<UsersDataSet> users = new ArrayList<>();

        try {
            users = dbService.getAllUsers();
        } catch (DBException e) {
            e.printStackTrace();
        }

        params.put("users", users);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("index.html", params));
    }
}
