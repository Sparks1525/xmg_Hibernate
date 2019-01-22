package temp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import temp.domain.Address;
import temp.domain.Company;

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);

    /**
     * 14:18:54.949 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into company (name, provice, city, street, reg_provice, reg_city, reg_street) values (?, ?, ?, ?, ?, ?, ?)
     * 14:18:55.063 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void save1(){
        Session session = null;

        Address address = new Address("qq1","ww1","ee1");
        Address regAddress = new Address("qq2","ww2","ee2");
        Company company = new Company("ccc",address,regAddress);

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            LOGGER.fatal("transaction begin");
            session.save(company);
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
     * 14:22:15.581 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select company0_.id as id1_0_0_, company0_.name as name2_0_0_, company0_.provice as provice3_0_0_, company0_.city as city4_0_0_, company0_.street as street5_0_0_, company0_.reg_provice as reg6_0_0_, company0_.reg_city as reg7_0_0_, company0_.reg_street as reg8_0_0_ from company company0_ where company0_.id=?
     * 14:22:15.688 [main] FATAL temp.test.Test - transaction commit
     * ----------事务关闭之后打印---------------
     * company.getAddress:Address{provice='qq1', city='ww1', street='ee1'}
     * company.getRegAddress:Address{provice='qq2', city='ww2', street='ee2'}
     */
    @org.junit.Test
    public void get1(){
        Session session = null;

        Company company = null;

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            LOGGER.fatal("transaction begin");
            company = (Company) session.get(Company.class,1L);
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
        System.out.println("----------事务关闭之后打印---------------");
        System.out.println("company.getAddress:" + company.getAddress());
        System.out.println("company.getRegAddress:" + company.getRegAddress());
    }



}
