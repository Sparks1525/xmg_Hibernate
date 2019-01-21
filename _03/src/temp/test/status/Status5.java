package temp.test.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Status5 {
    private static final Logger LOGGER = LogManager.getLogger(Status5.class);


    //=====================================游离状态 ->持久化状态======================================================


    @Test
    public void save() {
        /*
        控制台输出:
        16:11:48.536 [main] FATAL temp.test.status.Status1 - transaction begin
        Hibernate: insert into student (name, age) values (?, ?)
        16:11:48.661 [main] FATAL temp.test.status.Status1 - transaction commit
         */
        Session session = null;
        Student student = new Student(3L,"iiii",5);
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


    @Test
    public void saveOrUpdate() {
        /*
        有id控制台输出:
        16:40:22.238 [main] FATAL temp.test.status.Status5 - transaction begin
        16:40:22.323 [main] FATAL temp.test.status.Status5 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Session session = null;
        Student student = new Student(3L,"ppp",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.saveOrUpdate(student);
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

    @Test
    public void update1() {
        /*
        控制台输出:
        16:42:53.676 [main] FATAL temp.test.status.Status5 - transaction begin
        16:42:53.769 [main] FATAL temp.test.status.Status5 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Session session = null;
        Student student = new Student(3L,"ppp",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.update(student);
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



    @Test
    public void update2() {
        /*
        控制台输出:
        14:33:45.706 [main] FATAL temp.test.status.Status5 - transaction begin
        14:33:45.778 [main] FATAL temp.test.status.Status5 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Session session = null;
        Student student = new Student(3L,"ppp",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            student.setName("jjjjjjj");
            session.update(student);
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

    @Test
    public void update3() {
        /*
        控制台输出:
        14:35:27.551 [main] FATAL temp.test.status.Status5 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        14:35:27.658 [main] FATAL temp.test.status.Status5 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Session session = null;

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Student student = (Student) session.get(Student.class,3L);
            student.setName("j");
            session.update(student);
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

    @Test
    public void update4() {
        /*
        控制台输出:

         */
        Session session = null;

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            Student student = (Student) session.get(Student.class,3L);
            session.clear();
            student.setName("j");
            session.update(student);
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


    @Test
    public void update5() {
        /*
        控制台输出:
15:24:36.911 [main] FATAL temp.test.status.Status5 - transaction begin
Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session: [temp.domain.Student#3]
	at org.hibernate.engine.internal.StatefulPersistenceContext.checkUniqueness(StatefulPersistenceContext.java:697)
	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.performUpdate(DefaultSaveOrUpdateEventListener.java:293)
	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.entityIsDetached(DefaultSaveOrUpdateEventListener.java:239)
	at org.hibernate.event.internal.DefaultUpdateEventListener.performSaveOrUpdate(DefaultUpdateEventListener.java:55)
	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.onSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:90)
	at org.hibernate.internal.SessionImpl.fireUpdate(SessionImpl.java:785)
	at org.hibernate.internal.SessionImpl.update(SessionImpl.java:777)
	at org.hibernate.internal.SessionImpl.update(SessionImpl.java:773)
	at temp.test.status.Status5.update5(Status5.java:216)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:349)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:314)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:312)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:292)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:396)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
         */
        Session session = null;
        Student student2 = new Student(3L,"newsss",33);

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            // 报错原因: 一级缓存里有两个主键一样的对象 主键必须唯一
            Student student1 = (Student) session.get(Student.class,3L);
            session.update(student2);
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

    @Test
    public void merge1() {
        /*
        控制台输出:
        16:43:41.024 [main] FATAL temp.test.status.Status5 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        16:43:41.143 [main] FATAL temp.test.status.Status5 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Session session = null;
        Student student = new Student(3L,"hhhh",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.merge(student);
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

    @Test
    public void merge2() {
        /*
        控制台输出:
        16:54:34.895 [main] FATAL temp.test.status.Status5 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        16:54:35.027 [main] FATAL temp.test.status.Status5 - transaction commit
         */
        Session session = null;
        // student 与数据库的数据一致,并没有修改 则只发select语句 不发送update语句
        Student student = new Student(3L,"hhhh",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.merge(student);
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

    @Test
    public void merge3() {
        /*
        控制台输出:
        17:21:58.878 [main] FATAL temp.test.status.Status5 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        17:21:58.887 [main] FATAL temp.test.status.Status5 - transaction commit
         */
        Session session1 = null;
        Student student1 = null;
        try{
            session1 = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session1.beginTransaction();
            student1 = (Student) session1.get(Student.class,3L);
            LOGGER.fatal("transaction commit");
            session1.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session1 != null && session1.getTransaction().isActive()){
                session1.getTransaction().rollback();
            }
        } finally {
            if(session1 != null && session1.isOpen()){
                session1.close();
            }
        }

        Session session2 = null;
        try{
            session2 = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session2.beginTransaction();
            Student student2 = (Student)session2.get(Student.class, 3L);
            // 这里如果换成update会报错,原因在结尾
            session2.merge(student1);
            LOGGER.fatal("transaction commit");
            session2.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session2 != null && session2.getTransaction().isActive()){
                session2.getTransaction().rollback();
            }
        } finally {
            if(session2 != null && session2.isOpen()){
                session2.close();
            }
        }
    }



    @Test
    public void merge31() {
        /*
        15:17:44.651 [main] FATAL temp.test.status.Status5 - transaction begin
    Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
    org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session: [temp.domain.Student#3]
    at org.hibernate.engine.internal.StatefulPersistenceContext.checkUniqueness(StatefulPersistenceContext.java:697)
    at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.performUpdate(DefaultSaveOrUpdateEventListener.java:293)
    at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.entityIsDetached(DefaultSaveOrUpdateEventListener.java:239)
    at org.hibernate.event.internal.DefaultUpdateEventListener.performSaveOrUpdate(DefaultUpdateEventListener.java:55)
    at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.onSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:90)
    at org.hibernate.internal.SessionImpl.fireUpdate(SessionImpl.java:785)
    at org.hibernate.internal.SessionImpl.update(SessionImpl.java:777)
    at org.hibernate.internal.SessionImpl.update(SessionImpl.java:773)
    at temp.test.status.Status5.merge3(Status5.java:296)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:606)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:349)
    at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
    at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:314)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:312)
    at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:292)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:396)
    at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
    at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
    at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
    at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
    at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
     */
        Session session1 = null;
        Student student1 = null;
        try{
            session1 = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session1.beginTransaction();
            student1 = (Student) session1.get(Student.class,3L);
            LOGGER.fatal("transaction commit");
            session1.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session1 != null && session1.getTransaction().isActive()){
                session1.getTransaction().rollback();
            }
        } finally {
            if(session1 != null && session1.isOpen()){
                session1.close();
            }
        }

        Session session2 = null;
        try{
            session2 = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session2.beginTransaction();
            Student student2 = (Student)session2.get(Student.class, 3L);
            session2.update(student1);
            LOGGER.fatal("transaction commit");
            session2.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session2 != null && session2.getTransaction().isActive()){
                session2.getTransaction().rollback();
            }
        } finally {
            if(session2 != null && session2.isOpen()){
                session2.close();
            }
        }
    }




    @Test
    public void merge4() {
        /*
        控制台输出:
        17:28:13.110 [main] FATAL temp.test.status.Status5 - transaction begin
        (p:这条select是get()发出)
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        17:28:13.224 [main] FATAL temp.test.status.Status5 - transaction commit
        Hibernate: update student set name=?, age=? where id=?
         */
        Session session = null;

        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            // student 在session的一级缓存里   则merge不会再发select  详情在源码解释了
            Student student = (Student) session.get(Student.class,3L);
            student.setName("cccc");
            session.merge(student);
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

    @Test
    public void merge5() {
        /*
        控制台输出:
        17:16:15.341 [main] FATAL temp.test.status.Status5 - transaction begin
        Hibernate: select student0_.id as id1_0_0_, student0_.name as name2_0_0_, student0_.age as age3_0_0_ from student student0_ where student0_.id=?
        Hibernate: insert into student (name, age) values (?, ?)
        17:16:15.480 [main] FATAL temp.test.status.Status5 - transaction commit
         */
        Session session = null;
        // student 数据库并没有id为22的数据,merge会插入一条数据,但是id不一致
        Student student = new Student(22L,"mmmmm",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.merge(student);
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

    @Test
    public void merge6() {
        /*
        控制台输出:
        17:17:40.210 [main] FATAL temp.test.status.Status5 - transaction begin
        Hibernate: insert into student (name, age) values (?, ?)
        17:17:40.326 [main] FATAL temp.test.status.Status5 - transaction commit
         */
        Session session = null;
        // student 没有id时,merge会插入一条数据
        Student student = new Student("mmmmm",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.merge(student);
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

    @Test
    public void lock() {
        /*
        控制台输出:
         */
        Session session = null;
        Student student = new Student(1L,"mmmmm",5);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();

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
     * merge的作用是：新new一个对象，如果该对象设置了ID，则这个对象就当作游离态处理：
     *
     *                                       当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
     *
     *                                       当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
     *
     *                               如果没有设置ID的话，则这个对象就当作瞬态处理：
     *
     *                                用update的话，由于没有ID，所以会报异常，merge此时则会保存数据，根据ID生产策略生成一条数据；
     *
     * Session session1 = HibernateUtils.getSession();
     * Transaction transaction1 = session1.beginTransaction();
     * Students str1 = new Students();
     * str1.setStu_id(4);
     * str1.setName("222");
     * session1.merge(str1);
     * str1.setName("333");
     * transaction1.commit();
     * session1.clear();
     * session1.close();
     *
     * 下面是当对象在第一个session关闭后，处于游离状态，第二个session开启，又get或load一样的ID的数据出来时，在第二个session中update那个游离态对象，
     *
     *           update肯定会出错，原因是程序会报持久层中已经有该对象，因为第二个session重新从数据库中获取了一个对象成持久态，你的update会让那个游离态对象也变成持久态，两个持久态会冲突撒，然而用merge的话，它会把第一个的对象数据赋值给已经处于持久化的那个对象中，自己本身不得变为持久态；（这个我测试很多到的，没问题）
     *
     * Session session1 = HibernateUtils.getSession();
     *
     * Transaction transaction1 = session1.beginTransaction();
     *
     * Students str1 = (Students)session1.get(Students.class, 2);
     * transaction1.commit();
     * session1.clear();
     * session1.close();
     *
     * Session session2 = HibernateUtils.getSession();
     * Transaction transaction2 = session2.beginTransaction();
     * Students str2 = (Students)session2.get(Students.class, 2);
     * session2.merge(str1);
     * transaction2.commit();
     * session2.clear();
     * session2.close();
     *
     *
     *
     * Session session2 = HibernateUtils.getSession();
     * Transaction transaction2 = session2.beginTransaction();
     * Students str2 = (Students)session2.get(Students.class, 2);
     * str1.setName("wer");
     * session2.merge(str1);
     * System.out.println(str2.getName());  //这里改变了，说明持久态的数据也会改变
     * str2.setName("ee");
     * System.out.println(str1.getName());   //这里不会改变，说明第一个游离态的数据没有被持久化撒；
     * transaction2.commit();
     * session2.clear();
     * session2.close();
     * ---------------------
     * 作者：浪漫星空
     * 来源：CSDN
     * 原文：https://blog.csdn.net/lang_man_xing/article/details/7572964
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */


}
