package test;

import temp.dao.IStudentDAO;
import temp.dao.impl.StudentDAOImpl;

public class Test {

    @org.junit.Test
    public void test1(){

        IStudentDAO dao = new StudentDAOImpl();
        System.out.println(dao.get(Long.valueOf(2)));

    }
}
