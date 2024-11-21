package entities;

import enums.employee.EmployeeRole;
import enums.employee.EmployeeShift;
import enums.employee.EmployeeStatus;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name, surname, phone, address;
    private LocalDate hiringDate;
    private float salary;
    private EmployeeShift employeeShift;
    public EmployeeRole employeeRole;
    private EmployeeStatus employeeStatus;

    public Employee(int id, String name, String surname, String phone, String address, LocalDate hiringDate, float salary, EmployeeRole employeeRole, EmployeeShift employeeShift, EmployeeStatus employeeStatus) {
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

    public Employee() {
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

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public void setSalary(float salary) {
        this.salary = salary;
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
    @Override
    public String toString() {
        return "---------------------\n" +
                "Id: " + id + "\n" +
                "Nombre: " + name + "\n" +
                "Salario: " + salary + "\n" +
                "Apellido: " + surname + "\n" +
                "Telefono: " + phone + "\n" +
                "Direccion: " + address + "\n" +
                "Fecha contrato: " + hiringDate + "\n" +
                "Horario: " + employeeShift + "\n" +
                "Estado: " + employeeStatus + "\n"
                ;
    }



}
