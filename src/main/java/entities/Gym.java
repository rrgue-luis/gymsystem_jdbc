package entities;

import enums.gym.GymStatus;

import java.util.ArrayList;
import java.util.List;

public class Gym {

    private int id;
    private String name, address, phone, email;
    public GymStatus status;
    private String schedule;
    private List<Employee> employees = new ArrayList<>();
    private List<String> members = new ArrayList<>();


    public Gym() {
    }

    public Gym(int id, String name, String address, String schedule, String phone, String email, GymStatus status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schedule = schedule;
        this.phone = phone;
        this.email = email;
        this.status = status;
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

    @Override
    public String toString() {
        return "---------------------\n" +
                "ID: " + id + "\n" +
                "Nombre: " + name + "\n" +
                "Direccion: " + address + "\n" +
                "Telefono: " + phone + "\n" +
                "E-mail: " + email + "\n" +
                "Horario: " + schedule + "\n" +
                "Estado: " + status + "\n"
                ;
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

    public GymStatus getStatus() {
        return status;
    }
    public void setStatus(GymStatus status) {
        this.status = status;
    }

    public List<Employee> getEmployeeList() {
        return employees;
    }

    public void setEmployeeList(List<Employee> employeesList) {
        this.employees = employeesList;
    }

    public List<String> getRegisteredMembersList() {
        return members;
    }

    public void setRegisteredMembersList(List<String> membersList) {
        this.members = membersList;
    }

}
