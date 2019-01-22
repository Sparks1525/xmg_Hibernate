package temp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import temp.domain.QQNumber;
import temp.domain.QQZone;

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);


    /**
     * 09:35:37.391 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into qqnumber (number) values (?)
     * 09:35:37.502 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: insert into qqzone (title, id) values (?, ?)
     */
    @org.junit.Test
    public void save1() {
        Session sesson = null;

        QQNumber qqNumber = new QQNumber(2333);
        QQZone qqZone = new QQZone("qqzzzzz");
        qqNumber.setZone(qqZone);
        qqZone.setNumber(qqNumber);

        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(qqNumber);
            sesson.save(qqZone);

            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
    }


    /**
     * 09:36:11.073 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into qqnumber (number) values (?)
     * 09:36:11.181 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: insert into qqzone (title, id) values (?, ?)
     */
    @org.junit.Test
    public void save2() {
        Session sesson = null;

        QQNumber qqNumber = new QQNumber(2333);
        QQZone qqZone = new QQZone("qqzzzzz");
        qqNumber.setZone(qqZone);
        qqZone.setNumber(qqNumber);

        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(qqZone);
            sesson.save(qqNumber);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
    }

    /**
     * PS:
     * Hibernate.initialize(bill.getItems());
     * 可以使用Hibernate.initialize去初始化一个代理对象;
     */

    /**
     * 09:46:21.323 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select qqnumber0_.id as id1_0_1_, qqnumber0_.number as number2_0_1_, qqzone1_.id as id1_1_0_,
     * qqzone1_.title as title2_1_0_ from qqnumber qqnumber0_ left outer join qqzone qqzone1_ on qqnumber0_.id=qqzone1_.id where qqnumber0_.id=?
     * 09:46:21.427 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get1() {
        Session sesson = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            QQNumber qqNumber = (QQNumber) sesson.get(QQNumber.class,1L);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
    }

    /**
     * 09:48:13.696 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select qqnumber0_.id as id1_0_1_, qqnumber0_.number as number2_0_1_, qqzone1_.id as id1_1_0_,
     * qqzone1_.title as title2_1_0_ from qqnumber qqnumber0_ left outer join qqzone qqzone1_ on qqnumber0_.id=qqzone1_.id where qqnumber0_.id=?
     * 09:48:13.811 [main] FATAL temp.test.Test - transaction commit
     * qqNumber.getZone():QQZone{id=1, title='qqzzzzz'}
     */
    @org.junit.Test
    public void get2() {
        Session sesson = null;
        QQNumber qqNumber = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            qqNumber = (QQNumber) sesson.get(QQNumber.class,1L);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
        System.out.println("qqNumber.getZone():"+qqNumber.getZone());
    }

    /**
     * 09:49:27.557 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select qqzone0_.id as id1_1_0_, qqzone0_.title as title2_1_0_ from qqzone qqzone0_ where qqzone0_.id=?
     * 09:49:27.658 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get3() {
        Session sesson = null;
        QQZone qqZone = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            qqZone = (QQZone) sesson.get(QQZone.class,1L);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
        // System.out.println("qqZone.getNumber():"+qqZone.getNumber());
    }

    /**
     * 09:49:56.363 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select qqzone0_.id as id1_1_0_, qqzone0_.title as title2_1_0_ from qqzone qqzone0_ where qqzone0_.id=?
     * 09:49:56.467 [main] FATAL temp.test.Test - transaction commit
     *
     * org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     *
     *
     * 去掉constrained="true"即可运行
     */
    @org.junit.Test
    public void get4() {
        Session sesson = null;
        QQZone qqZone = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            qqZone = (QQZone) sesson.get(QQZone.class,1L);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
        System.out.println("qqZone.getNumber():"+qqZone.getNumber());
    }

    /**
     * 10:05:22.718 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select qqnumber0_.id as id1_0_1_, qqnumber0_.number as number2_0_1_, qqzone1_.id as id1_1_0_, qqzone1_.title as title2_1_0_ from qqnumber qqnumber0_ left outer join qqzone qqzone1_ on qqnumber0_.id=qqzone1_.id where qqnumber0_.id=?
     * 10:05:22.819 [main] FATAL temp.test.Test - transaction commit
     * qqNumber.getZone():null
     */
    @org.junit.Test
    public void get5() {
        Session sesson = null;
        QQNumber qqNumber = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            qqNumber = (QQNumber) sesson.get(QQNumber.class,3L);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (sesson != null && sesson.getTransaction().isActive()) {
                sesson.getTransaction().rollback();
            }
        } finally {
            if (sesson != null && sesson.isOpen()) {
                sesson.close();
            }
        }
        System.out.println("qqNumber.getZone():"+qqNumber.getZone());
    }
}
