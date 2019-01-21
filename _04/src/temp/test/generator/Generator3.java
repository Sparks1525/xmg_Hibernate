package temp.test.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;
import temp.domain.StudentUUID;

@SuppressWarnings("all")
public class Generator3 {
    private static final Logger LOGGER = LogManager.getLogger(Generator1.class);

    //=====================================native======================================================


    /**
     * 10:58:53.248 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * 10:58:53.353 [main] FATAL temp.test.generator.Generator1 - transaction commit
     */
    @Test
    public void save1(){
        Session session = null;
        Student student = new Student("navtive",43);
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
     * 11:00:28.088 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * Hibernate: insert into student (name, age) values (?, ?)
     * Hibernate: insert into student (name, age) values (?, ?)
     * 11:00:28.196 [main] FATAL temp.test.generator.Generator1 - transaction commit
     */
    @Test
    public void save2(){
        Session session = null;
        Student student1 = new Student("navtive1",43);
        Student student2 = new Student("navtive2",43);
        Student student3 = new Student("navtive3",43);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
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
     * 13:19:16.207 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * 13:19:16.318 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: update student set name=?, age=? where id=?
     */
    @Test
    public void save3(){
        Session session = null;
        Student student = new Student("navtive1",43);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            student.setName("nav");
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
     * 13:20:42.116 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * 13:20:42.225 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: delete from student where id=?
     */
    @Test
    public void save4(){
        Session session = null;
        Student student = new Student("navtive1",43);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            session.delete(student);
            student.setName("nav");
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
     * 13:23:10.302 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * 13:23:10.412 [main] FATAL temp.test.generator.Generator1 - transaction commit
     */
    @Test
    public void save5(){
        Session session = null;
        Student student = new Student("navtive1",43);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            session.clear();
            student.setName("nav");
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
