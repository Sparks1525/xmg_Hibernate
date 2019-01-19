package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status2 {
    private static final Logger LOGGER = LogManager.getLogger(Status2.class);


    //=====================================游离状态 -> 删除状态======================================================
    @Test
    public void delete() {
        /*
        控制台输出:
        16:19:12.423 [main] FATAL temp.test.status.Status1 - transaction begin
        16:19:12.503 [main] FATAL temp.test.status.Status1 - transaction commit
        Hibernate: delete from student where id=?
         */
        Session session = null;
        Student student = new Student(12L);
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
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
