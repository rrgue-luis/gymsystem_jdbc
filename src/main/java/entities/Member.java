package entities;

import java.text.SimpleDateFormat;

import java.util.Date;

public class Member {

    private int id;
    private String name, surname, gender, phone, address;
    private Date birthDate;


    private Date registrationDate;
    private Date membershipEndDate;
    public MembershipType membershipType;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public enum MembershipType{
        DAILY,
        WEEKLY,
        MONTHLY
    }

    public String FormattedBirthDate() {
        return formatter.format(birthDate);
    }

    public String FormattedRegistrationDate() {
        return formatter.format(registrationDate);
    }

    public String FormattedMembershipEndDate() {
        return formatter.format(membershipEndDate);
    }

    private void renewMembership(){
        //renovar membresia
        //if payment == valid ...
    }

    private void updateContactInfo(Date newEndDate, Payment payment){
        //actualizar datos de contacto
        //pedir x consola, etc
    }

    private String getMemberDetails(Member member){
        //mostrar datos de un miembro
        return member.toString();
    }

    public boolean isMemberShipActive(){
        //membresia activa o no
        return false;
    }

    public Member() {
    }

    public Member(int id, String name, String surname, String gender, String phone, String address, Date birthDate, Date registrationDate, Date membershipEndDate, Member.MembershipType membershipType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.membershipEndDate = membershipEndDate;
        this.membershipType = membershipType;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(Date membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }
}
