package temp.test.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Generator5 {
    private static final Logger LOGGER = LogManager.getLogger(Generator1.class);

    //=====================================assigned======================================================


    /**
     * 11:05:21.223 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * 11:05:21.308 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     */
    @Test
    public void save1(){
        Session session = null;
        Student student = new Student(50L,"assigned",43);
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
     * 11:06:26.228 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * 11:06:26.307 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     */
    @Test
    public void save2(){
        Session session = null;
        Student student1 = new Student(51L,"assigned1",43);
        Student student2 = new Student(52L,"assigned2",43);
        Student student3 = new Student(53L,"assigned3",43);
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
