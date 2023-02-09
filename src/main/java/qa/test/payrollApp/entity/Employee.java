package qa.test.payrollApp.entity;

import jakarta.persistence.*;
import qa.test.payrollApp.entity.dictionary.Role;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    private int companyId;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee(){};

    public Employee(int companyId, String name, String surname, Role role){
        this.companyId = companyId;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
