package temp.test.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Generator4 {
    private static final Logger LOGGER = LogManager.getLogger(Generator1.class);

    //=====================================identity======================================================


    /**
     * 11:02:26.392 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * 11:02:26.497 [main] FATAL temp.test.generator.Generator1 - transaction commit
     */
    @Test
    public void save1(){
        Session session = null;
        Student student = new Student("identity",43);
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
     * 11:03:08.535 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: insert into student (name, age) values (?, ?)
     * Hibernate: insert into student (name, age) values (?, ?)
     * Hibernate: insert into student (name, age) values (?, ?)
     * 11:03:08.644 [main] FATAL temp.test.generator.Generator1 - transaction commit
     */
    @Test
    public void save2(){
        Session session = null;
        Student student1 = new Student("identity1",43);
        Student student2 = new Student("identity2",43);
        Student student3 = new Student("identity3",43);
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

}
