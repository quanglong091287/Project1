package longtq2.controller.admin;

import longtq2.command.ListenGuidelineCommand;
import longtq2.core.common.utils.UploadUtil;
import longtq2.core.service.ListenGuidelineService;
import longtq2.core.service.impl.ListenGuidelineServiceImpl;
import longtq2.core.web.common.WebConstant;
import longtq2.core.web.utils.FormUtil;
import longtq2.core.web.utils.RequestUtil;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html","/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private ListenGuidelineService listenGuidelineService = new ListenGuidelineServiceImpl();
    private final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        command.setMaxPageItems(2);
        RequestUtil.initSearchBean(request, command);
//        Object[] objects = listenGuidelineService.findListenGuidelineByProperty(null, command.getFirstItem(), command.getSortExpression(),command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
//        command.setListResult((List<ListenGuidelineDTO>) objects[1]);
//        command.setTotalItems(Integer.parseInt(objects[0].toString()));
//        request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
//        request.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
        if(session != null){
           request.setAttribute(WebConstant.ALERT,session.getAttribute(WebConstant.ALERT));
           request.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));
        }
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        if(command.getTypeUrl() != null && command.getTypeUrl().equals(WebConstant.URL_LIST)){
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            rd.forward(request, response);
        }else if(command.getTypeUrl() != null && command.getTypeUrl().equals(WebConstant.URL_EDIT)){
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            rd.forward(request, response);
        }
        session.removeAttribute(WebConstant.ALERT);
        session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = new ListenGuidelineCommand();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
        HttpSession session = request.getSession();
        Set<String> valueTitle = buildSetValueListenGuideLine();
        try {
            Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, WebConstant.LISTENGUIDELINE);
            Map<String, String>  mapValue = (Map<String, String>) objects[3];
            command = returnValueListenGuideLineComand(valueTitle, command, mapValue);
            session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
        } catch (FileUploadException e) {
            log.error(e.getMessage(), e);
            session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.error"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.error"));
        }
        response.sendRedirect("/admin-guideline-listen-edit.html?typeUrl=url_list");
    }

    private ListenGuidelineCommand returnValueListenGuideLineComand(Set<String> valueTitle, ListenGuidelineCommand command, Map<String, String> mapValue) {
        for(String item: valueTitle){
            if(mapValue.containsKey(item)){
                if(item.equals("pojo.title")){
                    command.getPojo().setTitle(mapValue.get(item));
                }else if(item.equals("pojo.content")){
                    command.getPojo().setContent(mapValue.get(item));
                }
            }
        }
        return command;
    }

    private Set<String> buildSetValueListenGuideLine() {
        Set<String> returnValue = new HashSet<String>();
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        return returnValue;
    }

}
