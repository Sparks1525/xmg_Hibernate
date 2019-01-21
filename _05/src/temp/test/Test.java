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

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);



    /**
     * 16:12:36.825 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into department (name) values (?)
     * Hibernate: insert into employee (name, dept_id) values (?, ?)
     * 16:12:36.927 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void save1(){
        Session sesson = null;
        Department department = new Department("dept1");
        Employee employee = new Employee("emp1",department);

        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(department);
            sesson.save(employee);
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
     * 16:13:03.802 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into employee (name, dept_id) values (?, ?)
     * Hibernate: insert into department (name) values (?)
     * 16:13:03.905 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: update employee set name=?, dept_id=? where id=?
     */
    @org.junit.Test
    public void save2(){
        Session sesson = null;
        Department department = new Department("dept1");
        Employee employee = new Employee("emp1",department);

        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            // 先保存employee会额外发送update的sql语句
            sesson.save(employee);
            sesson.save(department);
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
     * 16:16:19.990 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into employee (name, dept_id) values (?, ?)
     * Hibernate: insert into employee (name, dept_id) values (?, ?)
     * Hibernate: insert into department (name) values (?)
     * 16:16:20.101 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: update employee set name=?, dept_id=? where id=?
     * Hibernate: update employee set name=?, dept_id=? where id=?
     */
    @org.junit.Test
    public void save3(){
        Session sesson = null;
        Department department1 = new Department("dept1");
        Employee employee1 = new Employee("emp1",department1);
        Employee employee2 = new Employee("emp2",department1);
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            // 先保存employee会额外发送update的sql语句
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
     * 1.通过many方拿one方,使用延迟加载;
     * 2.可以通过ifnull来判断many方是否有one方关联
     * 3.拿到的one方必须在session关闭之前实例化,否则不能使用(no session的错误)
     * 4.many方不是延迟加载,one方才是延迟加载
     */

    /**
     * 16:19:52.285 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 16:19:52.391 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get1(){
        Session sesson = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            Employee employee1 = (Employee) sesson.get(Employee.class,3L);
            LOGGER.debug("employee1:" + employee1);
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
     * 16:21:16.803 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * 16:21:16.902 [main] FATAL temp.test.Test - transaction commit
     *
     * org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     * ........
     */
    @org.junit.Test
    public void get2(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,3L);
            // 通过many方拿one使用延迟加载
            // 从many方得到one方必须在session关闭之前实例化one方,否则报no session错误
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

        LOGGER.debug("department:" + employee1.getDept());
    }

    /**
     * 16:23:30.558 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 16:23:30.662 [main] FATAL temp.test.Test - session department:Department{id=3, name='dept3'}
     * 16:23:30.662 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get3(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,3L);
            // 通过many方拿one使用延迟加载
            // 从many方得到one方必须在session关闭之前实例化one方,否则报no session错误
            LOGGER.fatal("session department:" + employee1.getDept());
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
        LOGGER.debug("department:" + employee1.getDept());
    }

    /**
     * 16:40:26.380 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * Hibernate: select department0_.id as id1_0_0_, department0_.name as name2_0_0_ from department department0_ where department0_.id=?
     * 16:40:26.497 [main] FATAL temp.test.Test - session:Employee{id=3, name='emp3', dept=Department{id=3, name='dept3'}}
     * 16:40:26.498 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get4(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,3L);
            // 通过many方拿one使用延迟加载
            // 从many方得到one方必须在session关闭之前实例化one方,否则报no session错误
            LOGGER.fatal("session:" + employee1);
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
        LOGGER.debug("department:" + employee1.getDept());
    }

    /**
     * 16:46:07.781 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * 16:46:07.879 [main] FATAL temp.test.Test - no dept
     * 16:46:07.879 [main] FATAL temp.test.Test - transaction commit
     * 16:46:07.890 [main] FATAL temp.test.Test - employee1:Employee{id=5, name='emp5', dept=null}
     */
    @org.junit.Test
    public void get5(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,5L);
            // 通可以通过ifnull来判断many方是否有one方关联
            if(employee1.getDept() == null){
                LOGGER.fatal("no dept");
            } else {
                LOGGER.fatal("have dept");
            }
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
        LOGGER.fatal("employee1:" + employee1);
        // 下面一句不会打印在控制台
        LOGGER.debug("department:" + employee1.getDept());
    }

    /**
     * 16:48:11.014 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * 16:48:11.114 [main] FATAL temp.test.Test - have dept
     * 16:48:11.115 [main] FATAL temp.test.Test - transaction commit
     *
     * org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     */
    @org.junit.Test
    public void get6(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,3L);
            // 通可以通过ifnull来判断many方是否有one方关联
            if(employee1.getDept() == null){
                LOGGER.fatal("no dept");
            } else {
                LOGGER.fatal("have dept");
            }
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

        // 下面两句任何一句都会报错,原因是没有实例化dept
        LOGGER.fatal("employee1:" + employee1);
        LOGGER.debug("department:" + employee1.getDept());
    }


    /**
     * 16:50:36.894 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * 16:50:36.992 [main] FATAL temp.test.Test - have dept
     * 16:50:36.992 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get7(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,3L);
            // 通可以通过ifnull来判断many方是否有one方关联
            if(employee1.getDept() == null){
                LOGGER.fatal("no dept");
            } else {
                LOGGER.fatal("have dept");
            }
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
     * 16:58:07.851 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select employee0_.id as id1_1_0_, employee0_.name as name2_1_0_, employee0_.dept_id as dept3_1_0_ from employee employee0_ where employee0_.id=?
     * clz:class temp.domain.Department_$$_javassist_0
     * temp.domain.Department_$$_javassist_0
     * temp.domain.Department_$$_javassist_0
     * temp.domain.Department_$$_javassist_0
     * temp.domain.Department_$$_javassist_0
     * --------------------------------
     * equals
     * toString
     * hashCode
     * clone
     * getName
     * getId
     * setName
     * writeReplace
     * getHandler
     * setHandler
     * getHibernateLazyInitializer
     * _d12toString
     * _d7hashCode
     * _d5getId
     * _d0clone
     * _d6getName
     * _d11setName
     * _d1equals
     * _d10setId
     * setId
     * 16:58:07.966 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get8(){
        Session sesson = null;
        Employee employee1 = null;
        try{
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            employee1 = (Employee) sesson.get(Employee.class,3L);

            Department department = employee1.getDept();

            Class clz = department.getClass();
            System.out.println("clz:" + clz);

            Constructor[] cons = clz.getConstructors();
            for(Constructor con : cons){
                System.out.println(con.getName());
            }
            System.out.println("--------------------------------");
            Method[] methods = clz.getDeclaredMethods();
            for(Method method : methods){
                System.out.println(method.getName());
            }


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
