package temp.test;

import temp.dao.IStudentDAO;
import temp.dao.impl.StudentDAO;
import temp.domain.Student;

public class Test {
    public static void main(String[] args) {
        IStudentDAO dao = new StudentDAO();
        Student student = dao.get(1L);
        System.out.println(student);
    }
}
