package entities;

import java.util.Date;

public class Employee {

    private int id;
    private String name, surname, phone;
    private Date hiringDate;
    private float salary;

    private role role;

    private enum status{
        ACTIVE,
        INACTIVE,
        VACATIONS;
    }

    private enum role{
        BOSS,
        MANAGER,
        TRAINER,
        EMPLOYEE;
    }

    private void updateRole(){
        //actualizar rol
    }
    private void updateContactInfo(){
        //ACTUALIZAR INFFO DE CONTACTO
    }
    private Employee getEmployeeDetails(Employee employee){
        //mostrar detalles del empleado
        return employee;
    }

    public Employee() {
    }

    public Employee(int id, String name, String surname, String phone, Date hiringDate, float salary, Employee.role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.role = role;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee.role getRole() {
        return role;
    }

    public void setRole(Employee.role role) {
        this.role = role;
    }
}
