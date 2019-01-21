package temp.domain;

public class StudentTG {
    private Long id;
    private String name;
    private Integer age;

    public StudentTG() {
    }

    public StudentTG(Long id) {
        this.id = id;
    }

    public StudentTG(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public StudentTG(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
