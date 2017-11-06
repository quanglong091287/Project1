package longtq2.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
// interface viet phuong thuc khong co ten ham
//interface la ham con cua ham class.
// genericDao la ham con cua AbstractDao
// tai sao phai dung interfacc hien thi nhung thuoc tinh rieng thang con ma thang cha k co

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();
    T update(T entity);
    public void save(T entity);
    T finfById(ID id);
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Integer delete(List<ID> ids);
}

