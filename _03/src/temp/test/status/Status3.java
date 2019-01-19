package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status3 {
    private static final Logger LOGGER = LogManager.getLogger(Status3.class);


    //=====================================持久化状态 -> 删除状态======================================================

    /**
     * 持久化状态 -> 删除状态
     */
    @Test
    public void delete(){
        /*
        控制台输出:
        16:17:33.885 [main] FATAL temp.test.status.Status1 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        16:17:34.002 [main] FATAL temp.test.status.Status1 - session.get()
        16:17:34.012 [main] FATAL temp.test.status.Status1 - transaction commit
        Hibernate: delete from student where id=?
         */
        Session session = null;
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Student student = (Student)session.get(Student.class,11L);
            LOGGER.fatal("session.get()");
            session.delete(student);
            LOGGER.fatal("transaction commit");
            session.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }



}
