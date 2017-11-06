package longtq2.core.data.daoimpl;

import longtq2.core.common.constant.CoreConstant;
import longtq2.core.common.utils.HibernateUtil;
import longtq2.core.data.dao.GenericDao;
import org.hibernate.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private  Class<T> persitenceClass;
    public AbstractDao(){
        this.persitenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }
    // ham chuyen tu class sang String de dung cau lenh sql
    public String getPersitenceClassName(){
        return persitenceClass.getSimpleName();
    }

//    protected Session getSession(){
//        return HibernateUtil.getSessionFactory().openSession();
//    }
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        // bat ngoai le duoc nem ra boi transaction
        try {

            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersitenceClassName());// doan nay k append User ma fai dung ham chuyen doi string
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public T finfById(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            result = (T) session.get(persitenceClass, id);
            if(result == null){
                throw new ObjectNotFoundException("NOT FOUND " + id , null);
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection , Integer offset, Integer limit) {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem = 0;
        String[] params = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i= 0;
        for(Map.Entry<String, Object> item: property.entrySet()){
            params[i] = (String) item.getKey();
            values[i] = item.getValue();
            i++;
        }
        try {
            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersitenceClassName());
            if(property.size() > 0){
                for(int i1 = 0; i1 < params.length; i1++){
                    if(i1 == 0){
                        sql1.append(" where ").append(params[i1]).append("= :"+params[i1]+"");
                    }else {
                        sql1.append(" and ").append(params[i1]).append("= :"+params[i1]+"");
                    }
                }
            }
            if(sortExpression != null && sortDirection != null) {
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " +(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            if(property.size() > 0){
                for(int i2 = 0; i2 < params.length; i2++){
                    query1.setParameter(params[i2], values[i2]);
                }
            }
            if(offset != null && offset >= 0 ){
                query1.setFirstResult(offset);
            }
            if(limit != null && limit > 0 ){
                query1.setMaxResults(limit);
            }
            list = query1.list();

            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersitenceClassName());
            if(property.size() > 0){
                for(int k = 0; k < params.length; k++){
                    if(k == 0){
                        sql2.append(" where ").append(params[k]).append("= :"+params[k]+"");
                    }else {
                        sql2.append(" and ").append(params[k]).append("= :"+params[k]+"");
                    }
                }
            }
            Query query2 =session.createQuery(sql2.toString());
            if(property.size() > 0){
                for(int k1 = 0; k1 < params.length; k1++){
                    query2.setParameter(params[k1], values[k1]);
                }
            }
            totalItem = query2.list().get(0);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID item: ids){
                T t = (T) session.get(persitenceClass,item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return count;
    }
}
