package temp.dao;

import temp.domain.Student;

import java.util.List;

public interface IStudentDAO {

    void save(Student student);
    void update(Student student);
    void delete(Student student);
    List<Student> list();
    Student get(Long id);
}
