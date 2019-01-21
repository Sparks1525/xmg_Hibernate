package temp.test.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.StudentUUID;

@SuppressWarnings("all")
public class Generator2 {
    private static final Logger LOGGER = LogManager.getLogger(Generator1.class);

    //=====================================UUID======================================================

    /**
     * DDL:
     *
     * CREATE TABLE `studentuuid` (
     *   `sid` varchar(255) NOT NULL,
     *   `name` varchar(255) DEFAULT NULL,
     *   `age` tinyint(4) DEFAULT NULL,
     *   PRIMARY KEY (`sid`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */


    /**
     * 10:52:35.575 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * 10:52:35.652 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into studentUUID (name, age, sid) values (?, ?, ?)
     * Disconnected from the target VM, address: '127.0.0.1:2029', transport: 'socket'
     * <p>
     * SQLDATA:
     * sid:40284713686e52ed01686e52efb10000 name:uuid1 age:43
     */
    @Test
    public void save1() {
        Session session = null;
        StudentUUID studentUUID = new StudentUUID("uuid1", 43);
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(studentUUID);
            LOGGER.fatal("transaction commit");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * 11:01:26.726 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * 11:01:26.827 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into studentUUID (name, age, sid) values (?, ?, ?)
     * Hibernate: insert into studentUUID (name, age, sid) values (?, ?, ?)
     * Hibernate: insert into studentUUID (name, age, sid) values (?, ?, ?)
     */
    @Test
    public void save2() {
        Session session = null;
        StudentUUID studentUUID1 = new StudentUUID("uuid1", 43);
        StudentUUID studentUUID2 = new StudentUUID("uuid2", 43);
        StudentUUID studentUUID3 = new StudentUUID("uuid3", 43);
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(studentUUID1);
            session.save(studentUUID2);
            session.save(studentUUID3);
            LOGGER.fatal("transaction commit");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * 13:47:12.593 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * 13:47:12.680 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into studentUUID (name, age, sid) values (?, ?, ?)
     * Hibernate: update studentUUID set name=?, age=? where sid=?
     */
    @Test
    public void save3() {
        Session session = null;
        StudentUUID studentUUID = new StudentUUID("uuid3", 43);
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(studentUUID);
            studentUUID.setName("name uuid");
            LOGGER.fatal("transaction commit");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
