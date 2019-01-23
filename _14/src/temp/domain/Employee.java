package temp.domain;

public class Employee {
    private Long id;
    private String name;
    private Department dept;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String name, Department dept) {
        this.name = name;
        this.dept = dept;
    }

    public Employee(Long id, String name, Department dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
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

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
