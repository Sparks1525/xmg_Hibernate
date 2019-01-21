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
    public void save1(){
        Session sesson = null;
        Employee employee1 = new Employee("emp1_06");
        Employee employee2 = new Employee("emp2_06");

        Department department1 = new Department();
        department1.setName("departemnt1_06");
        department1.getEmps().add(employee1);
        department1.getEmps().add(employee2);
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(employee1);
            sesson.save(employee2);
            sesson.save(department1);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(sesson != null && sesson.getTransaction().isActive()){
                sesson.getTransaction().rollback();
            }
        } finally {
            if(sesson != null && sesson.isOpen()){
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
    public void save2(){
        Session sesson = null;
        Employee employee1 = new Employee("emp1_06");
        Employee employee2 = new Employee("emp2_06");
        Department department1 = new Department();
        department1.setName("departemnt1_06");
        department1.getEmps().add(employee1);
        department1.getEmps().add(employee2);
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(department1);
            sesson.save(employee1);
            sesson.save(employee2);
            LOGGER.fatal("transaction commit");
            sesson.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(sesson != null && sesson.getTransaction().isActive()){
                sesson.getTransaction().rollback();
            }
        } finally {
            if(sesson != null && sesson.isOpen()){
                sesson.close();
            }
        }
    }



}
