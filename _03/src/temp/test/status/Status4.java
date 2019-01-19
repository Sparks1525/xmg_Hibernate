package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status4 {
    private static final Logger LOGGER = LogManager.getLogger(Status4.class);


    //=====================================持久化状态 -> 游离状态======================================================
    @Test
    public void close() {
        /*
        控制台输出:
        16:34:31.224 [main] FATAL temp.test.status.Status4 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        16:34:31.339 [main] FATAL temp.test.status.Status4 - transaction commit
        student:IStudentDAO{id=6, name='ttt', age=5}
         */
        Session session = null;
        Student student = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            student  = (Student)session.get(Student.class,6L);
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

        System.out.println("student:" + student);
    }

    @Test
    public void clear() {
        /*
        控制台输出:
        16:35:23.842 [main] FATAL temp.test.status.Status4 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        16:35:23.948 [main] FATAL temp.test.status.Status4 - transaction commit
        student:IStudentDAO{id=6, name='ttt', age=5}
         */
        Session session = null;
        Student student = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            student  = (Student)session.get(Student.class,6L);
            LOGGER.fatal("transaction commit");
            session.clear();
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

        System.out.println("student:" + student);
    }


    @Test
    public void evict() {
        /*
        控制台输出:
        16:36:14.678 [main] FATAL temp.test.status.Status4 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        16:36:14.791 [main] FATAL temp.test.status.Status4 - transaction commit
        student:IStudentDAO{id=6, name='ttt', age=5}
         */
        Session session = null;
        Student student = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            student  = (Student)session.get(Student.class,6L);
            LOGGER.fatal("transaction commit");
            session.evict(student);
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

        System.out.println("student:" + student);
    }



}
