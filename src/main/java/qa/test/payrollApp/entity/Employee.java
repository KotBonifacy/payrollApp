package qa.test.payrollApp.entity;

import jakarta.persistence.*;
import qa.test.payrollApp.entity.dictionary.EmployeeStatus;
import qa.test.payrollApp.entity.dictionary.Role;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int employeeId;

    @ManyToOne
    @JoinColumn(name = "id")
    private Company company;

    private String name;

    private String surname;

    private String documentId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    public Employee(){};

    public Employee(Company company, String name, String surname, String documentId, Role role){
        this.company = company;
        this.name = name;
        this.surname = surname;
        this.documentId = documentId;
        this.role = role;
        this.status = EmployeeStatus.hired;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int id) {
        this.employeeId = id;
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

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public void fireEmployee(){
        this.status = EmployeeStatus.fired;
    }
}
