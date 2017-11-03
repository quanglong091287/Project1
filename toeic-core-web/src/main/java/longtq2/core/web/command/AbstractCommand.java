package longtq2.core.web.command;

import java.util.List;
// tao ra lop cha, khi muon dung thi chi goi va dung
public class AbstractCommand<T> {
    protected T pojo;//pojo la plain old java object.lop java bean thuan
    private String crudaction;// dung de them sua xoa cac chuc nang
    private List<T> listResult; //dung trong display tag
    private String tableId = "tableList";
    private int maxPageItems = 5;
    private int totalItems = 0;
    private int firstItem = 0;
    private String sortExpression;
    private String sortDirection;
    private String[] checkList;
    private String messageResponse;
    private int page = 1;
    private String typeUrl;

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public String getCrudaction() {
        return crudaction;
    }

    public void setCrudaction(String crudaction) {
        this.crudaction = crudaction;
    }
}
