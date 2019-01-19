package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status1 {
    private static final Logger LOGGER = LogManager.getLogger(Status1.class);

    //=====================================临时状态 ->持久化状态======================================================

    @Test
    public void save() {
        /*
        控制台输出:
        16:11:48.536 [main] FATAL temp.test.status.Status1 - transaction begin
        Hibernate: insert into student (name, age) values (?, ?)
        16:11:48.661 [main] FATAL temp.test.status.Status1 - transaction commit
         */
        Session session = null;
        Student student = new Student("iiii",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
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


    @Test
    public void saveOrUpdate() {
        /*
        没有id控制台输出:
        16:10:45.722 [main] FATAL temp.test.status.Status1 - transaction begin
        Hibernate: insert into student (name, age) values (?, ?)
        16:10:45.848 [main] FATAL temp.test.status.Status1 - transaction commit
         */
        Session session = null;
        Student student = new Student("ppp",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.saveOrUpdate(student);
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

    @Test
    public void persist() {
        /*
        控制台输出:
        16:12:32.405 [main] FATAL temp.test.status.Status1 - transaction begin
        Hibernate: insert into student (name, age) values (?, ?)
        16:12:32.524 [main] FATAL temp.test.status.Status1 - transaction commit
         */
        Session session = null;
        Student student = new Student("eee",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.persist(student);
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
