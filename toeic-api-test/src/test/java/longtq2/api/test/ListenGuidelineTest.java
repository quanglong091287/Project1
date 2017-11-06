package longtq2.api.test;

import longtq2.core.dao.ListenGuidelineDao;
import longtq2.core.daoimpl.ListenGuidelineDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ListenGuidelineTest {
    ListenGuidelineDao listenGuidelineDao;
    @BeforeTest
    public void initData(){
        listenGuidelineDao = new ListenGuidelineDaoImpl();
    }

    @Test
    public void checkFindByProperties() {
        Map<String, Object> property = new HashMap<String, Object>();
        property.put("title","Bai hd 1");
        property.put("content","Noi dung bai hd 1");
        Object[] objects = listenGuidelineDao.findByProperty(property,null, null,null,null);
    }
}
