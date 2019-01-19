package temp.test;

import temp.dao.IStudentDAO;
import temp.dao.impl.StudentDAO;
import temp.domain.Student;

public class Test {

    IStudentDAO dao = new StudentDAO();

    @org.junit.Test
    public void get1() {
        Student student = dao.get(1L);
        System.out.println(student);
    }

    @org.junit.Test
    public void save1(){
        Student student = new Student("zz",22);
        dao.save(student);
    }

    @org.junit.Test
    public void delete1(){
        dao.delete(10L);
    }

    @org.junit.Test
    public void update1(){
        Student student = new Student(6L,"kk",24);
        dao.update(student);
    }

}
