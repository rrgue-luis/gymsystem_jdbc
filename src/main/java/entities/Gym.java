package entities;

import java.util.ArrayList;
import java.util.List;

public class Gym {

    private int id;
    private String name, address, schedule, phone, email;
    private int capacity;
    private status status;

    private List<Employee> employeeList = new ArrayList<>();
    private List<String> registeredMembersList = new ArrayList<>();
    private enum status{
        OPEN,
        CLOSED,
        MAINTENANCE;
    }

    private void addEmployee(){
        Employee employee = new Employee();
        //agregar employee
    }

    private void removeEmployee(){
        //borrar employee [de esta sucursal]
    }

    private void addMember(){
        Member member = new Member();
        //agregar member
    }

    private Gym getGymDetails(Gym gym){
        //mostrar detalles del gym
        return gym;
    }

    public Gym() {
    }

    public Gym(int id, String name, String address, String schedule, String phone, String email, int capacity, Gym.status status, List<Employee> employeeList, List<String> registeredMembersList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schedule = schedule;
        this.phone = phone;
        this.email = email;
        this.capacity = capacity;
        this.status = status;
        this.employeeList = employeeList;
        this.registeredMembersList = registeredMembersList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Gym.status getstatus() {
        return status;
    }

    public void setstatus(Gym.status status) {
        this.status = status;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<String> getRegisteredMembersList() {
        return registeredMembersList;
    }

    public void setRegisteredMembersList(List<String> registeredMembersList) {
        this.registeredMembersList = registeredMembersList;
    }
}
