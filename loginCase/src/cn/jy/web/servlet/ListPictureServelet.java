package cn.jy.web.servlet;

import cn.jy.domain.User;
import cn.jy.service.UserService;
import cn.jy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/listPictureServlet")
public class ListPictureServelet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user's URL list in cloud.
        UserService service = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String[] urls = user.getUrl_address().split(",");

        //send back URL list to list.jsp to display all pictures the user uploaded
        request.setAttribute("urls", urls);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
