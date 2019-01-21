package temp.domain;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private Long id;
    private String name;
    private Set<Employee> emps = new HashSet<>();

    public Department() {
    }

    public Department(Long id) {
        this.id = id;
    }

    public Department(String name, Set<Employee> emps) {
        this.name = name;
        this.emps = emps;
    }

    public Department(Long id, String name, Set<Employee> emps) {
        this.id = id;
        this.name = name;
        this.emps = emps;
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

    public Set<Employee> getEmps() {
        return emps;
    }

    public void setEmps(Set<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
