package temp.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import temp.dao.IStudentDAO;
import temp.domain.Student;

import java.util.List;

@SuppressWarnings("all")
public class StudentDAO implements IStudentDAO {

    @Override
    public void save(Student student) {
        Session session = null;
        try{
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
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

    @Override
    public void update(Student student) {
        Session session = null;
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            session.update(student);
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

    @Override
    public void delete(Long id) {
        Session session = null;
        Student student = new Student(id);
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Student get(Long id) {
        Session session = null;
        Student student = null;
        try {
            session = new Configuration().configure().buildSessionFactory().openSession();
            session.beginTransaction();
            student = (Student) session.get(Student.class,id);
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
        return student;
    }

    @Override
    public List<Student> list() {
        return null;
    }
}
