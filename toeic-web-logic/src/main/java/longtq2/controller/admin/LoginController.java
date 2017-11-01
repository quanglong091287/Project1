package longtq2.controller.admin;

import longtq2.command.UserCommand;
import longtq2.core.dto.UserDTO;
import longtq2.core.service.UserService;
import longtq2.core.service.impl.UserServiceImpl;
import longtq2.core.web.common.WebConstant;
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
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        UserService userService = new UserServiceImpl();
        try{
            if(userService.isUserExits(pojo) != null){
                if(userService.findRoleByUser(pojo) != null && userService.findRoleByUser(pojo).getRoleDTO() != null){
                    if(userService.findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)){
                        request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE,"Đăng nhập thành công");
                    }else if(userService.findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_USER)){
                        request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE,"Đăng nhập thành công1");
                    }
                }
            }
        }catch (NullPointerException e){
            log.error(e.getMessage(), e);
            request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE,"Tên hoặc mật khẩu không đúng");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request, response);
    }
}
