package org.example;

import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class MyServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/android";
    private static final String USER = "root";
    private static final String PASS = "vit@l26041993";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM test");) {
            StringBuilder jsonResponse = new StringBuilder();
            jsonResponse.append("["); // начало массива

            boolean first = true;
            while (rs.next()) {
                if (!first) {
                    jsonResponse.append(","); // разделитель для элементов массива
                }
                int id = rs.getInt("id");
                String testcol = rs.getString("testcol");

                jsonResponse.append("{"); // начало объекта
                jsonResponse.append("\"id\":").append(id).append(",");
                jsonResponse.append("\"testcol\":\"").append(testcol).append("\"");
                jsonResponse.append("}"); // конец объекта

                first = false;
            }
            jsonResponse.append("]"); // конец массива

            resp.getWriter().write(jsonResponse.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
