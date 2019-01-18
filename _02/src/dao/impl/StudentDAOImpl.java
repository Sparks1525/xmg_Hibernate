package dao.impl;

import dao.IStudentDAO;
import domain.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;

@SuppressWarnings("all")
public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student student) {
        Session session = null;
        try {
            session = new Configuration().configure()
                    .buildSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
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
        Student student = null;
        Session session = null;
        try {
            session = new Configuration().configure()
                    .buildSessionFactory().openSession();
            session.beginTransaction();
            student = (Student) session.get(Student.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return student;
    }
}
