package temp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import temp.domain.Employee;

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);

    /**
     * //得到二级缓存对象
     *   Cache cache=sf.getCache();
     * //剔除一个实例
     *   cache.evictEntity(User.class, 1L);
     * //剔除某种类型的所有实例
     *   cache.evictEntityRegion(User.class);
     * //剔除所有二级缓存实例
     *   cache.evictEntityRegions();
     */

    @org.junit.Test
    public void get1(){
        Session session = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            LOGGER.fatal("transaction begin");


            session.get(Employee.class,1L);
            session.get(Employee.class,1L);
            session.get(Employee.class,1L);
            session.get(Employee.class,2L);


            LOGGER.fatal("transaction commit");
            session.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }


}
