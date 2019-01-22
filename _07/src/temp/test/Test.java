package temp.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import temp.domain.Student;
import temp.domain.Teacher;

@SuppressWarnings("all")
public class Test {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);


    /**
     * 14:37:57.113 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into Teacher (name) values (?)
     * Hibernate: insert into Student (name) values (?)
     * 14:37:57.223 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: insert into STU_TEA (TEACHER_ID, STUDENT_ID) values (?, ?)
     */
    @org.junit.Test
    public void save1() {
        Session sesson = null;
        Student student = new Student("lsi");
        Teacher teacher = new Teacher("wangw");
        student.getTeachers().add(teacher);
        teacher.getStudents().add(student);

        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(teacher);
            sesson.save(student);
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
     * 14:38:40.855 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: insert into Student (name) values (?)
     * Hibernate: insert into Teacher (name) values (?)
     * 14:38:40.971 [main] FATAL temp.test.Test - transaction commit
     * Hibernate: insert into STU_TEA (TEACHER_ID, STUDENT_ID) values (?, ?)
     */
    @org.junit.Test
    public void save2() {
        Session sesson = null;
        Student student = new Student("lsi");
        Teacher teacher = new Teacher("wangw");
        student.getTeachers().add(teacher);
        teacher.getStudents().add(student);

        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            sesson.save(student);
            sesson.save(teacher);
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
     * 14:40:45.265 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select student0_.id as id1_1_0_, student0_.name as name2_1_0_ from Student student0_ where student0_.id=?
     * 14:40:45.385 [main] FATAL temp.test.Test - transaction commit
     * <p>
     * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: temp.domain.Student.teachers, could not initialize proxy - no Session
     */
    @org.junit.Test
    public void get1() {
        Session sesson = null;
        Student student = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            student = (Student) sesson.get(Student.class, 1L);
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
        System.out.println("student.getTeachers:" + student.getTeachers());
    }

    /**
     * 14:42:45.288 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select student0_.id as id1_1_0_, student0_.name as name2_1_0_ from Student student0_ where student0_.id=?
     * Hibernate: select teachers0_.STUDENT_ID as STUDENT2_1_1_, teachers0_.TEACHER_ID as TEACHER1_0_1_, teacher1_.id as id1_2_0_, teacher1_.name as name2_2_0_ from STU_TEA teachers0_ inner join Teacher teacher1_ on teachers0_.TEACHER_ID=teacher1_.id where teachers0_.STUDENT_ID=?
     * student.getTeachers:[Teacher{id=1, name='wangw'}]
     * 14:42:45.405 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get2() {
        Session sesson = null;
        Student student = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            student = (Student) sesson.get(Student.class, 1L);
            Hibernate.initialize(student.getTeachers());
            System.out.println("student.getTeachers:" + student.getTeachers());
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
     * 14:44:45.219 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select teacher0_.id as id1_2_0_, teacher0_.name as name2_2_0_ from Teacher teacher0_ where teacher0_.id=?
     * 14:44:45.329 [main] FATAL temp.test.Test - transaction commit
     *
     * org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: temp.domain.Teacher.students, could not initialize proxy - no Session
     */
    @org.junit.Test
    public void get3() {
        Session sesson = null;
        Teacher teacher = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            teacher = (Teacher) sesson.get(Teacher.class, 1L);
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
        System.out.println("teacher.getStudents:" + teacher.getStudents());
    }

    /**
     * 14:45:30.593 [main] FATAL temp.test.Test - transaction begin
     * Hibernate: select teacher0_.id as id1_2_0_, teacher0_.name as name2_2_0_ from Teacher teacher0_ where teacher0_.id=?
     * Hibernate: select students0_.TEACHER_ID as TEACHER1_2_1_, students0_.STUDENT_ID as STUDENT2_0_1_, student1_.id as id1_1_0_, student1_.name as name2_1_0_ from STU_TEA students0_ inner join Student student1_ on students0_.STUDENT_ID=student1_.id where students0_.TEACHER_ID=?
     * teacher.getStudents:[Student{id=1, name='lsi'}]
     * 14:45:30.705 [main] FATAL temp.test.Test - transaction commit
     */
    @org.junit.Test
    public void get4() {
        Session sesson = null;
        Teacher teacher = null;
        try {
            sesson = new Configuration().configure().buildSessionFactory().openSession();
            sesson.beginTransaction();
            LOGGER.fatal("transaction begin");
            teacher = (Teacher) sesson.get(Teacher.class, 1L);
            Hibernate.initialize(teacher.getStudents());
            System.out.println("teacher.getStudents:" + teacher.getStudents());
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
