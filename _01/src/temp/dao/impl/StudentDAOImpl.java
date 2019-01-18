package temp.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import temp.dao.IStudentDAO;
import temp.domain.Student;

import java.util.List;

@SuppressWarnings("all")
public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student student) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();



        session.close();
        sessionFactory.close();

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public List<Student> list() {
        return null;
    }

    @Override
    public Student get(Long id) {

        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Student student = (Student) session.get(Student.class, id);

        session.close();
        sessionFactory.close();


        return student;
    }
}
