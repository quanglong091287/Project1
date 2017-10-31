package longtq2.controller.admin;

import longtq2.command.UserCommand;
import longtq2.core.dto.UserDTO;
import longtq2.core.web.utils.FormUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login.html")
public class LoginController extends HttpServlet {
    // de su dung log4j khai bao
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        UserCommand command = FormUtil.populate(UserCommand.class, request);
//        UserDTO pojo = command.getPojo();
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request, response);
    }
}
