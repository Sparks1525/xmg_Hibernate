package temp.domain;

public class StudentUUID {
    private String sid;
    private String name;
    private Integer age;

    public StudentUUID() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public StudentUUID(String sid, String name, Integer age) {
        this.sid = sid;
        this.name = name;
        this.age = age;
    }

    public StudentUUID(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "IStudentDAO{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
