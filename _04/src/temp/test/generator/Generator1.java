package temp.test.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import temp.domain.Student;

@SuppressWarnings("all")
public class Generator1 {
    private static final Logger LOGGER = LogManager.getLogger(Generator1.class);

    //=====================================increment======================================================

    /**
     * DDL:
     *
     * CREATE TABLE `student` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
     *   `name` varchar(255) DEFAULT NULL,
     *   `age` int(11) DEFAULT NULL,
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
     */

    /**
     * 10:22:09.663 [main] FATAL temp.test.status.Status1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:22:09.762 [main] FATAL temp.test.status.Status1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     */
    @Test
    public void save1(){
        Session session = null;
        Student student = new Student("nnn",43);
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
     * 10:25:12.550 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:25:12.649 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     */
    @Test
    public void save2(){
        Session session = null;
        Student student1 = new Student("n1",41);
        Student student2 = new Student("n2",42);
        Student student3 = new Student("n3",43);
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
     * 10:26:31.176 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:26:31.276 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: update student set name=?, age=? where id=?   (p:更新的是student2)
     */
    @Test
    public void save3(){
        Session session = null;
        Student student1 = new Student("c1",31);
        Student student2 = new Student("c2",32);
        Student student3 = new Student("c3",33);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            student2.setName("c222");
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
     * 10:32:45.436 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:32:45.542 [main] FATAL temp.test.generator.Generator1 - student.id=12
     * 10:32:45.545 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: delete from student where id=?
     */
    @Test
    public void save4(){
        Session session = null;
        Student student = new Student("m2",32);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            LOGGER.fatal("student.id="+student.getId());
            session.delete(student);
            student.setName("save4");
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
     * 10:38:20.346 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:38:20.456 [main] FATAL temp.test.generator.Generator1 - student=IStudentDAO{id=12, name='m2', age=32}
     * 10:38:20.459 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: update student set name=?, age=? where id=?
     */
    @Test
    public void save5(){
        Session session = null;
        Student student = new Student("m2",32);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            LOGGER.fatal("student="+student);
            student.setName("save5");
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

    /**
     * 10:39:54.825 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:39:54.947 [main] FATAL temp.test.generator.Generator1 - student=IStudentDAO{id=13, name='m2', age=32}
     * org.hibernate.ObjectDeletedException: deleted instance passed to update(): [<null entity name>#<null>]
     * 	at org.hibernate.event.internal.DefaultUpdateEventListener.performSaveOrUpdate(DefaultUpdateEventListener.java:48)
     * 	at org.hibernate.event.internal.DefaultSaveOrUpdateEventListener.onSaveOrUpdate(DefaultSaveOrUpdateEventListener.java:90)
     * 	at org.hibernate.internal.SessionImpl.fireUpdate(SessionImpl.java:785)
     * 	at org.hibernate.internal.SessionImpl.update(SessionImpl.java:777)
     * 	at org.hibernate.internal.SessionImpl.update(SessionImpl.java:773)
     * 	at temp.test.generator.Generator1.save6(Generator1.java:201)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:606)
     * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
     * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
     * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     * 	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
     * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:349)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
     * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
     * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:314)
     * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
     * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:312)
     * 	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
     * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:292)
     * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:396)
     * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     * 	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
     * 	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
     * 	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
     */
    @Test
    public void save6(){
        Session session = null;
        Student student = new Student("m2",32);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            LOGGER.fatal("student="+student);
            student.setName("save6");
            session.delete(student);
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

    /**
     * 10:43:14.390 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:43:14.493 [main] FATAL temp.test.generator.Generator1 - student=IStudentDAO{id=13, name='m2', age=32}
     * 10:43:14.495 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: delete from student where id=?
     */
    @Test
    public void save7(){
        Session session = null;
        Student student = new Student("m2",32);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            LOGGER.fatal("student="+student);
            student.setName("save7");
            // 以下两条sql语句只yusave6()方法中的语句对换顺序  却不报错
            session.update(student);
            session.delete(student);
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
     * 10:41:54.300 [main] FATAL temp.test.generator.Generator1 - transaction begin
     * Hibernate: select max(id) from student
     * 10:41:54.409 [main] FATAL temp.test.generator.Generator1 - before student=IStudentDAO{id=13, name='m2', age=32}
     * 10:41:54.412 [main] FATAL temp.test.generator.Generator1 - after student=IStudentDAO{id=13, name='save8', age=32}
     * 10:41:54.412 [main] FATAL temp.test.generator.Generator1 - transaction commit
     * Hibernate: insert into student (name, age, id) values (?, ?, ?)
     * Hibernate: delete from student where id=?
     */
    @Test
    public void save8(){
        Session session = null;
        Student student = new Student("m2",32);
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            LOGGER.fatal("transaction begin");
            session.beginTransaction();
            session.save(student);
            LOGGER.fatal("before student="+student);
            session.delete(student);
            student.setName("save8");
            LOGGER.fatal("after student="+student);
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
