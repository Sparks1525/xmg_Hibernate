package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status6 {
    private static final Logger LOGGER = LogManager.getLogger(Status6.class);


    //=====================================无状态->持久化状态======================================================


    @Test
    public void get() {
        /*
        控制台输出:
        17:52:55.268 [main] FATAL temp.test.status.Status6 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        17:52:55.368 [main] FATAL temp.test.status.Status6 - transaction commit
         */
        Session session = null;

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Student student = (Student) session.get(Student.class,3L);
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
    public void load1() {
        /*
        控制台输出:
        17:55:24.726 [main] FATAL temp.test.status.Status6 - transaction begin
        17:55:24.792 [main] FATAL temp.test.status.Status6 - transaction commit
         */
        Session session = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Student student = (Student) session.load(Student.class,3L);
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
    public void load2() {
        /*
        控制台输出:
        17:56:17.381 [main] FATAL temp.test.status.Status6 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        17:56:17.492 [main] FATAL temp.test.status.Status6 - transaction commit
         */
        Session session = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Student student = (Student) session.load(Student.class,3L);
            // student 有使用到hibernate才会去数据库中查询
            student.getName();
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
    public void query() {
        /*
        控制台输出:

         */
        Session session = null;
        Student student = new Student(3L,"iiii",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Query query = session.createQuery("SELECT * FROM temp.domain.Student");

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
    public void iterator() {
        /*
        控制台输出:

         */
        Session session = null;
        Student student = new Student(3L,"iiii",5);
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





}
