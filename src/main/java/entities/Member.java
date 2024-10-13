package entities;

import business.impl.MemberServiceImp;

import java.time.LocalDate;

public class Member {

    private int id;
    private String name, surname, gender, phone, address;
    private LocalDate birthDate;

    private LocalDate registrationDate;
    private LocalDate membershipEndDate;
    private MembershipType membershipType;



    public enum MembershipType{
        DAILY,
        WEEKLY,
        MONTHLY;

        public static MembershipType fromString(String type) {
            try {
                return MembershipType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de membresia invalido, recibido: " + type + " Esperado: ('DAILY', 'WEEKLY', 'MONTHLY')");
            }
        }

    }

    public Member() {
    }

    public Member(int id, String name, String surname, String gender, String phone, String address, LocalDate birthDate, LocalDate registrationDate, Member.MembershipType membershipType, LocalDate membershipEndDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.membershipType = membershipType;
        this.membershipEndDate = membershipEndDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(LocalDate membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    MemberServiceImp memberServiceImp;

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    @Override
    public String toString() {
        return "---------------------\n" +
                "Id: " + id + "\n" +
                "Nombre: " + name + "\n" +
                "Apellido: " + surname + "\n" +
                "Genero: " + gender + "\n" +
                "Telefono: " + phone + "\n" +
                "Direccion: " + address + "\n" +
                "F. Nacimiento: " + birthDate + "\n" +
                "F. Registro: " + registrationDate + "\n" +
                "F. Memb. Vencimiento: " + membershipEndDate + "\n" +
                "Tipo de membresía: " + membershipType + "\n"
                ;
    }
}
