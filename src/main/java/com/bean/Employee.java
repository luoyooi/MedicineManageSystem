package com.bean;

import java.util.Objects;

/**
 * @author shkstart
 * @create 2020-05-30 15:27
 */
public class Employee {
    private String empName;
    private Integer age;
    private Boolean gender;
    private String phone;
    private String email;
    private String loginName;
    private String loginPassword;
    private Boolean isUse;
    private Boolean isManager;

    public Employee() {
    }

    public Employee(String empName, Integer age, Boolean gender, String phone, String email, String loginName, String loginPassword, Boolean isUse, Boolean isManager) {
        this.empName = empName;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.isUse = isUse;
        this.isManager = isManager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empName='" + empName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", isUse=" + isUse +
                ", isManager=" + isManager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empName, employee.empName) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(loginName, employee.loginName) &&
                Objects.equals(loginPassword, employee.loginPassword) &&
                Objects.equals(isUse, employee.isUse) &&
                Objects.equals(isManager, employee.isManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empName, age, gender, phone, email, loginName, loginPassword, isUse, isManager);
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

}
