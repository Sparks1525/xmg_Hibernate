package temp.test.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;
import temp.domain.StudentTG;

@SuppressWarnings("all")
public class Generator6 {
    private static final Logger LOGGER = LogManager.getLogger(Generator1.class);

    //=====================================TableGenerator======================================================


    /**
     * hibernate.cfg.xml配置 <property name="hibernate.hbm2ddl.auto">update</property>
     *
     *
     *
     * 11:18:40.754 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
     * Hibernate: insert into hibernate_sequences (sequence_name, next_val)  values (?,?)
     * Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
     * 11:18:40.876 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     *
     * 数据库中表hibernate_sequences的数据为 default 2
     */
    @Test
    public void save1(){
        Session session = null;
        StudentTG student = new StudentTG("TableGenerator",43);
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
     * 11:21:19.104 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
     * Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
     * Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
     * Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
     * Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
     * Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
     * 11:21:19.255 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     */
    @Test
    public void save2(){
        Session session = null;
        StudentTG student1 = new StudentTG("TableGenerator1",43);
        StudentTG student2 = new StudentTG("TableGenerator2",43);
        StudentTG student3 = new StudentTG("TableGenerator3",43);
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
     * 11:30:31.528 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
     * Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
     * 11:30:31.652 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     *
     * <param name="increment_size">100</param>
     */
    @Test
    public void save3(){
        Session session = null;
        StudentTG student1 = new StudentTG("TableGenerator1",43);
        StudentTG student2 = new StudentTG("TableGenerator2",43);
        StudentTG student3 = new StudentTG("TableGenerator3",43);
        StudentTG student4 = new StudentTG("TableGenerator4",43);
        StudentTG student5 = new StudentTG("TableGenerator5",43);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.save(student4);
            session.save(student5);
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
     * 13:05:10.531 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
     * Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
     * 13:05:10.659 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student_tg (name, age, id) values (?, ?, ?)
     * Hibernate: delete from student_tg where id=?
     */
    @Test
    public void save4(){
        Session session = null;
        StudentTG student1 = new StudentTG("TableGenerator1",43);
        StudentTG student2 = new StudentTG("TableGenerator2",43);
        StudentTG student3 = new StudentTG("TableGenerator3",43);
        StudentTG student4 = new StudentTG("TableGenerator4",43);
        StudentTG student5 = new StudentTG("TableGenerator5",43);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.save(student4);
            session.delete(student4);
            session.save(student5);
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
