package temp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import temp.domain.Department;
import temp.domain.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);


    /**
     * 17:54:08.359 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into employee (name) values (?)
     * Hibernate: insert into employee (name) values (?)
     * Hibernate: insert into department (name) values (?)
     * 17:54:08.478 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: update employee set dept_id=? where id=?
     * Hibernate: update employee set dept_id=? where id=?
     */
    @org.junit.Test
    public void save1() {
        Session sesson = null;
        Employee employee1 = new Employee("emp1_06");
        Employee employee2 = new Employee("emp2_06");

        Department department1 = new Department();
        department1.setName("departemnt1_06");
        department1.getEmps().add(employee1);
        department1.getEmps().add(employee2);
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(employee1);
            sesson.save(employee2);
            sesson.save(department1);
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
     * 17:53:46.882 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into department (name) values (?)
     * Hibernate: insert into employee (name) values (?)
     * Hibernate: insert into employee (name) values (?)
     * 17:53:47.019 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: update employee set dept_id=? where id=?
     * Hibernate: update employee set dept_id=? where id=?
     */
    @org.junit.Test
    public void save2() {
        Session sesson = null;
        Employee employee1 = new Employee("emp1_06");
        Employee employee2 = new Employee("emp2_06");
        Department department1 = new Department();
        department1.setName("departemnt1_06");
        department1.getEmps().add(employee1);
        department1.getEmps().add(employee2);
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(department1);
            sesson.save(employee1);
            sesson.save(employee2);
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
     * 1.通过one方得到many方,是一个延迟加载
     * 2.one方中的many方属性是一个集合,这个集合只能使用接口
     * 3.不能通过ifnull来判断one方是否有many方;只能通过集合的size()方法来判断;
     * 4.在session关闭之前必须使用集合对象,否则会报no session错误
     */

    /**
     * 08:41:05.193 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 08:41:05.301 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get1() {
        Session sesson = null;
        Department department = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            department = (Department) sesson.get(Department.class, 3L);
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
     * 08:39:32.279 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 08:39:32.387 [main] FATAL temp.test.Test - transaction commit
     *
     * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: temp.domain.Department.emps, could not initialize proxy - no Session
     */
    @org.junit.Test
    public void get2() {
        Session sesson = null;
        Department department = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            department = (Department) sesson.get(Department.class, 3L);
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
        LOGGER.fatal("department..getEmps():" + department.getEmps());
    }


    /**
     * 08:42:52.772 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * Hibernate: select emps0_.dept_id as dept3_0_1_, emps0_.id as id1_1_1_, emps0_.id as id1_1_0_, emps0_.name as name2_1_0_ from employee emps0_ where emps0_.dept_id=?
     * [Employee{id=3, name='emp3'}, Employee{id=4, name='emp4'}]
     * 08:42:52.905 [main] FATAL temp.test.Test - transaction commit
     * 08:42:52.922 [main] FATAL temp.test.Test - department..getEmps():[Employee{id=3, name='emp3'}, Employee{id=4, name='emp4'}]
     */
    @org.junit.Test
    public void get3() {
        Session sesson = null;
        Department department = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            department = (Department) sesson.get(Department.class, 3L);
            System.out.println(department.getEmps());
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
        LOGGER.fatal("department..getEmps():" + department.getEmps());
    }

    /**
     * 08:43:47.753 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 08:43:47.867 [main] FATAL temp.test.Test - transaction commit
     *
     * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: temp.domain.Department.emps, could not initialize proxy - no Session
     */
    @org.junit.Test
    public void get4() {
        Session sesson = null;
        Department department = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            department = (Department) sesson.get(Department.class, 3L);
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
        LOGGER.fatal("department..getEmps():" + department.getEmps());
    }


    /**
     * 08:50:08.702 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 08:50:08.810 [main] FATAL temp.test.Test - have dept
     * 08:50:08.810 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get5() {
        Session sesson = null;
        Department department = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            department = (Department) sesson.get(Department.class, 3L);
            if(department.getEmps() == null){
                LOGGER.fatal("no dept");
            } else {
                LOGGER.fatal("have dept");
            }
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
     * 08:49:08.195 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * Hibernate: select emps0_.dept_id as dept3_0_1_, emps0_.id as id1_1_1_, emps0_.id as id1_1_0_, emps0_.name as name2_1_0_ from employee emps0_ where emps0_.dept_id=?
     * 08:49:08.311 [main] FATAL temp.test.Test - have dept
     * 08:49:08.311 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get6() {
        Session sesson = null;
        Department department = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            department = (Department) sesson.get(Department.class, 3L);
            if(department.getEmps().size() <= 0){
                LOGGER.fatal("no dept");
            } else {
                LOGGER.fatal("have dept");
            }
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




}
