package longtq2.core.web.utils;

import longtq2.core.web.command.AbstractCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static void initSearchBean(HttpServletRequest request, AbstractCommand bean){
        if(bean != null){
            String sortExpression = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));
            String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));
            String pageStr = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
            Integer page = 1;
            if(StringUtils.isNotBlank(pageStr)){
                try{
                    page = Integer.valueOf(pageStr);
                }
                catch (Exception e){
                    //ignore
                }
            }
            bean.setPage(page);
            bean.setSortExpression(sortExpression);
            bean.setSortDirection(sortDirection);
            bean.setFirstItem((bean.getPage()- 1) * bean.getMaxPageItems());
        }
    }
}
