package com.ataraxia.backend.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String firstName;

    @Column (nullable = false)
    private String lastName;

    @Column (nullable = false, unique = true)
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column (nullable = false)
    private LocalDate hireDate;

    @Column (nullable = false)
    private String position;

    @Column(nullable = false)
    private Double salary;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user; 

    public Employee () {
    }

    public Employee(String firstName, String lastName, String email, String phone, String address, LocalDate hireDate, String position, Double salary, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.hireDate = hireDate;
        this.position = position;
        this.salary = salary;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id != null && id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", hireDate=" + hireDate +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", user=" + user +
                '}';
    }

}
