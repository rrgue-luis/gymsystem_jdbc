package entities;

import java.time.LocalDate;
import java.util.Date;

public class Employee {

    private int id;
    private String name, surname, phone, address;
    private LocalDate hiringDate;
    private float salary;

    private EmployeeRole employeeRole;
    private EmployeeShift employeeShift;

    private EmployeeStatus employeeStatus;

    public enum EmployeeStatus{
        ACTIVE,
        INACTIVE,
        VACATIONS;
    }

    public enum EmployeeShift {
        MORNING,
        AFTERNOON,
        NIGHT;
    }

    public enum EmployeeRole{
        BOSS,
        MANAGER,
        TRAINER,
        EMPLOYEE;

        public static EmployeeRole fromString(String type) {
            try {
                return EmployeeRole.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de rol inválido, recibido: " + type + " Esperado: ('BOSS', 'MANAGER, 'TRAINER', 'EMPLOYEE')");
            }
        }

    }

    public Employee() {
    }

    public Employee(int id, String name, String surname, String phone, String address, LocalDate hiringDate, float salary, Employee.EmployeeRole employeeRole, Employee.EmployeeShift employeeShift, Employee.EmployeeStatus employeeStatus) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.employeeRole = employeeRole;
        this.employeeShift = employeeShift;
        this.employeeStatus = employeeStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public EmployeeShift getEmployeeShift() {
        return employeeShift;
    }

    public void setEmployeeShift(EmployeeShift employeeShift) {
        this.employeeShift = employeeShift;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
