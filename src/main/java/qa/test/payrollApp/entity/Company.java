package qa.test.payrollApp.entity;

import jakarta.persistence.*;
import qa.test.payrollApp.entity.dictionary.CompanyStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private int companyId;

    private String name;

    private int taxId;

    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company(){};

    public Company(String name, int taxId, CompanyStatus companyStatus){
        this.name = name;
        this.taxId = taxId;
        this.companyStatus = companyStatus;
        employees = new ArrayList<>();
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int id) {
        this.companyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public CompanyStatus getStatus() {
        return companyStatus;
    }

    public void setStatus(CompanyStatus companyStatus) {
        this.companyStatus = companyStatus;
    }
}
