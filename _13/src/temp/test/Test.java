package temp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import temp.domain.Department;
import temp.domain.Employee;
import temp.domain.EmployeeVO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);

    /**
     * Hibernate中的查询方式:
     * 1,使用HQL:使用hibernate提供的面向对象的查询语句;
     * 2,使用SQL:在hibernate中允许使用原生的SQL直接查询;
     * 3,使用Criteria:hibernate提供的完全的面向对象的查询方式;
     * <p>
     * <p>
     * 1,HQL:
     * HQL的学习方法HQL是面向对象的,但是HQL借鉴了SQL的语法结构,把SQL中关系模型的概念替换成面向对象的概念;
     */

    @org.junit.Test
    public void hql1() {
        Session session = null;
        session = new Configuration().configure().buildSessionFactory().openSession();
        String hql = "SELECT e FROM Employee e WHERE e.name LIKE ? AND e.id BETWEEN ? AND ?";
        List<Employee> ret = session.createQuery(hql).setParameter(0, "%4%").setParameter(1, 1L)
                .setParameter(2, 10L).list();
        System.out.println(ret);
        session.close();
    }

    /**
     * 使用SQL,查询到的结果是一个Object[]的列表
     */
    @org.junit.Test
    public void hql2() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        String sql = "SELECT * FROM EMPLOYEE e WHERE e.name LIKE ? AND e.id BETWEEN ? AND ?";
        List<Employee> ret = session.createSQLQuery(sql)
                .setParameter(0, "%4%").setParameter(1, 1L)
                .setParameter(2, 10L).list();
        System.out.println(ret);
        session.close();
    }

    @org.junit.Test
    public void hql3() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();

        List<Employee> es = session.createCriteria(Employee.class)
                .add(Restrictions.like("name", "4", MatchMode.ANYWHERE))
                .add(Restrictions.between("id", 1L, 10L)).list();
        System.out.println(es);
        session.close();
    }

    /**
     * 分页:
     * 1,分页需要些什么东西?总条数,每一页需要多少条数据,当前是第几页,当前页的数据;
     * 2,查询当前页的数据,对于mysql来说,LIMIT ?,?
     * 3,使用query.setFirstResult()方法来设置从第几条数据开始查询;
     * 4,使用query.setMaxResult()方法来设置查询多少条数据;
     * 5,setFristResult和setMaxResult对于SQL和Criteria的查询都有效;
     */
    @org.junit.Test
    public void hql4() {

        int currentPage = 0;
        int pageSize = 0;
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        String hql = "SELECT e FROM Employee e WHERE e.id BETWEEN ? AND ? AND e.name LIKE ?";
        List<Employee> es = session.createQuery(hql)
                .setParameter(0, 1L)
                .setParameter(1, 10L)
                .setParameter(2, "%4%")
                .setFirstResult((currentPage - 1) * pageSize) //query.setFirstResult==LIMIT的第一个参数,代表从第几个数据开始查询
                .setMaxResults(pageSize) //query.setMaxResult==LIMIT的第二个参数,代表最大查询多少条数据
                .list();
        System.out.println(es);
        session.close();
    }

    /**
     * 查询总条数:
     * 但是使用这种方式非常的不方便,因为我们知道我们查询出来的结果就只有一行数据;
     */
    @org.junit.Test
    public void hql5() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        List<Employee> ret = session.createQuery("SELECT COUNT(e) FROM Employee e").list();
        System.out.println(ret.get(0));
        session.close();
    }

    /**
     * query.uniqueResult();
     * 使用uniqueResult,这个方法可以真正的去执行查询
     * 注意,这个方法仅仅只能用在我们确定结果集只有一行数据的时候,如果查询结果多余一条数据,报错
     * uniqueResult方法对于SQL和Criteria都有效
     */
    @org.junit.Test
    public void hql6() {
        Session session = null;
        session = new Configuration().configure().buildSessionFactory().openSession();
        Long count = (Long) session.createQuery("SELECT COUNT(e) FROM Employee e").uniqueResult();
        System.out.println(count);
        session.close();
    }

    /**
     * 查询参数设置:
     * 位置占位符:就是使用?号来代表查询参数,通过setParameter(index,object)来根据?的位置来设置参数的;
     * 1,写HQL的时候很方便;
     * 2,如果参数值不多,还是比较容易识别;
     * 3,如果参数值过多,会造成索引不容易识别;如果调整参数位置,所有的设置参数的位置都要变;如果一个参数在多个条件中使用,必须重复设置;
     * <p>
     * 名称占位符:
     * 1,使用 :参数名称  格式来添加名称占位符;
     * 2,使用setParamter(String name,object)这个方法为名称占位符添加参数;
     * 3,可以为多个参数起相同名字的名称占位符,在设置参数的时候只需要设置一次值,就可以在所有的位置设置好参数;
     * 4,使用名称占位符可以给参数传列表值进去,很容易的完成in等查询;但是使用位置占位不行(只能直接拼在HQL里面);
     */
    @org.junit.Test
    public void hql7() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        String hql = "SELECT e FROM Employee e WHERE e.name LIKE :name AND e.id BETWEEN :low AND :hi";
        List<Employee> ret = session.createQuery(hql)
                .setParameter("hi", 10L)
                .setParameter("name", "%4&4")
                .setParameter("low", 1L).list();
        System.out.println(ret);
        session.close();
    }

    @org.junit.Test
    public void hql8() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        String hql = "SELECT e FROM Employee e WHERE e.id IN (:ids)";
        List<Employee> ret = session.createQuery(hql)
                .setParameter("ids", new Long[]{1L, 10L, 20L, 30L}).list();
        System.out.println(ret);
        session.close();
    }

    /**
     * 3,可以通过setEntity方法直接给HQL设置一个实体对象的参数,
     * hibernate会自动的根据实体的关系,创建好对应的SQL
     */
    @org.junit.Test
    public void hql9() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        Department d = new Department();
        d.setId(1L);

        String hql = "SELECT e FROM Employee e WHERE e.dept = ?";
        List<Employee> ret = session.createQuery(hql)
                .setEntity(0, d).list();
        System.out.println(ret);
        session.close();
    }

    /**
     * 查询结果:
     * 1,查询一个实体对象;
     * 直接查询实体对象返回的是实体对象的列表;注意,这个列表中所有的对象都是持久化对象,所以如果查询的数据量过大,记得分页+及时清空一级缓存;
     * 2,投影查询;
     * 1,查询一个简单属性;
     */

    //查询一个简单属性,返回这个属性类型的list
    @org.junit.Test
    public void hql10() {

        Session session = new Configuration().configure().buildSessionFactory().openSession();
        List<Employee> ret = session.createQuery("SELECT e.name FROM Employee e ").list();
        System.out.println(ret);
        session.close();
    }

    //如果查询的属性是一个实体对象,返回这个实体对象的列表
    //这个列表中所有的对象也都是持久化对象
    //使用属性的导航查询,使用JOIN查询
    @org.junit.Test
    public void hql11() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();
        List<Department> depts = session.createQuery("SELECT e.dept FROM Employee e ").list();
        System.out.println(depts);
        session.close();
    }

    //如果查询多个属性,结果是这些属性的Object[]的列表
    @org.junit.Test
    public void hql12() {
        Session session = new Configuration().configure().buildSessionFactory().openSession();

        List<Object[]> ret = session.createQuery("SELECT e.id,e.name,e.salary FROM Employee e ").list();
        for (Object[] os : ret) {
            System.out.println(Arrays.toString(os));
        }

        session.close();
    }

    //如果查询多个属性,结果是这些属性的Object[]的列表
    //如果查询的属性里面有实体对象属性,那么在查询结果对应的位置上面,还是这些实体对象,并且这些实体对象是持久化的
    @org.junit.Test
    public void hql13() {

        Session session = session = new Configuration().configure().buildSessionFactory().openSession();

        List<Object[]> ret = session.createQuery("SELECT e.id,e.name,e.salary,e.dept FROM Employee e ").list();
        for (Object[] os : ret) {
            System.out.println(Arrays.toString(os));
        }

        session.close();

    }


    /**
     * hibernate查询结果的封装:
     * 员工的id,员工的工资,员工的姓名,员工对应部门的编号和部门名称,员工所在的城市
     */

    //查询结果返回Object[]
    @org.junit.Test
    public void hql14() {
        Session session = session = new Configuration().configure().buildSessionFactory().openSession();
        String hql="SELECT e.id,e.name,e.salary,e.dept.name,e.dept.sn,e.dept.address.city FROM Employee e";
        List<Object[]> ret = session.createQuery(hql).list();
        for (Object[] os : ret) {
            System.out.println(Arrays.toString(os));
        }
        session.close();
    }

    //使用new List把每一行查询结果包装成一个List对象
    @org.junit.Test
    public void hql15() {
        Session session = session = new Configuration().configure().buildSessionFactory().openSession();
        String hql="SELECT new list(e.id,e.name,e.salary,e.dept.name,e.dept.sn,e.dept.address.city) FROM Employee e";
        List<Object[]> ret = session.createQuery(hql).list();
        for (Object[] os : ret) {
            System.out.println(os);
        }
        session.close();
    }

    //使用new Map把每一行查询结果包装成一个MAP对象
    //1,默认情况下,把查询的属性的位置作为map的key,
    //2,可以给查询的属性添加别名,别名就可以作为map的key,查询的结果作为map的value
    @org.junit.Test
    public void hql16() {
        Session session = session = new Configuration().configure().buildSessionFactory().openSession();
        String hql="SELECT NEW MAP(e.id as eid,e.name as ename,e.salary as esalary,e.dept.name as dname," +
                "e.dept.sn as dsn,e.dept.address.city as city) FROM Employee e";
        List<Map<String,Object>> ret = session.createQuery(hql).list();
        for (Map<String,Object> os: ret) {
            System.out.println(os);
        }
        session.close();
    }


    //使用new Map把每一行查询结果包装成一个MAP对象
    //1,默认情况下,把查询的属性的位置作为map的key,
    //2,可以给查询的属性添加别名,别名就可以作为map的key,查询的结果作为map的value
    @org.junit.Test
    public void hql17() {
        Session session = session = new Configuration().configure().buildSessionFactory().openSession();
        String hql="SELECT NEW EmployeeVO(e.id as eid,e.name as ename,e.salary as esalary,e.dept.name as dname," +
                "e.dept.sn as dsn,e.dept.address.city as city) FROM Employee e";
        List<EmployeeVO> ret = session.createQuery(hql).list();
        for (EmployeeVO os: ret) {
            System.out.println(os);
        }
        session.close();
    }



    /**
     * NamedQuery查询:
     * 在hibernate中，执行查询需要先将HQL先翻译成SQL，再执行SQL。如果HQL比较复杂翻译的效率是比较低的。如果一条HQL重复执行，会重复翻译。效率低下。
     *   如果在代码不同的地方重复使用到了相同的HQL,需要在不同的地方反复重写HQL;
     * hibernate提供了NamedQuery方式，来稍微提高静态HQL语句的执行效率。和对HQL的统一管理
     * NamedQuery使用：
     * 在实体映射文件中添加：
     * <!--为HQL起名为findCustomersByName，该HQL在hibernate启动的时候就会翻译成SQL -->
     * <query name="findCustomersByName">
     *      <![CDATA[from Customer c where c.name like :name]]>
     * </query>
     * 查询的时候使用：
     * //通过getNamedQuery，得到的就是已经翻译为SQL的query对象，只需要设置参数查询就行了
     * NamedQuery的使用限制：NamedQuery里面只能配置静态的HQL。
     */
}
