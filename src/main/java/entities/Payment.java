package entities;

import java.util.Date;

public class Payment {

    private int id;
    private Member member;
    private float amount;
    private Date paymentDate;

    private paymentMethod paymentMethod;
    private enum paymentMethod{
        CREDIT,
        DEBIT,
        CASH,
        TRANSFER;
    }

    public void processPayment(){
        //procesar pago
    }
    public Payment getPaymentDetails(Payment payment){
        //mostrar datos del pago
        return payment;
    }
    public boolean isPaymentValid(){
        //es valido el pago? if else
        return false;
    }

    public Payment() {
    }

    public Payment(int id, Member member, float amount, Date paymentDate, Payment.paymentMethod paymentMethod) {
        this.id = id;
        this.member = member;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment.paymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment.paymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
