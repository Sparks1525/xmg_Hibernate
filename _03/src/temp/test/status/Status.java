package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status {
    private static final Logger LOGGER = LogManager.getLogger(Status.class);
    /**
     * 临时状态 ->持久化状态
     */
    @Test
    public void save1() {
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


    /**
     * 临时状态 ->持久化状态
     */
    @Test
    public void saveOrUpdate() {
        /*
        有id控制台输出:
        16:09:10.982 [main] FATAL temp.test.status.Status1 - transaction begin
        16:09:11.070 [main] FATAL temp.test.status.Status1 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
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

    /**
     * 临时状态 ->持久化状态
     */
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

    /**
     * 游离状态 ->持久化状态
     */
    @Test
    public void update() {
        /*
        控制台输出:
        16:13:14.687 [main] FATAL temp.test.status.Status1 - transaction begin
        16:13:14.799 [main] FATAL temp.test.status.Status1 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Student student = new Student(6L,"ttt",5);
        Session session = null;
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.update(student);
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

    /**
     * 游离状态 -> 删除状态
     */
    @Test
    public void delete1() {
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


    /**
     * 持久化状态 -> 删除状态
     */
    @Test
    public void delete2(){
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


    @Test
    public void get() {
        Session session = null;
        Student student = null;
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            student = (Student) session.get(Student.class,6L);
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
