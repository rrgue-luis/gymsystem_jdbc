package entities;

import java.time.LocalDate;

public class Payment {

    private int id;
    private int memberId;

    private int gymId;
    private float amount;
    private LocalDate paymentDate;
    private PaymentMethod paymentMethod;
    private boolean paymentIsValid;


    public enum PaymentMethod{
        CREDIT,
        DEBIT,
        CASH,
        TRANSFER;

        public static PaymentMethod fromString(String type) {
            try {
                return PaymentMethod.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de pago invalido, recibido: " + type + " Esperado: ('CASH', 'TRANSFER', 'CREDIT', 'DEBIT')");
            }
        }

    }

    @Override
    public String toString() {
        return "---------------------\n" +
                "ID: " + id + "\n" +
                "ID Miembro: "+ memberId + "\n" +
                "ID Gym: " + gymId + "\n" +
                "Cantidad: " + amount + "\n" +
                "Fecha: " + paymentDate + "\n" +
                "Metodo: " + paymentMethod + "\n" +
                "Valido: " + paymentIsValid
                ;
    }

    public Payment() {
    }

    public Payment(int id, int gymId, float amount, LocalDate paymentDate, PaymentMethod paymentMethod, int memberId, Boolean paymentIsValid) {
        this.id = id;
        this.gymId = gymId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentIsValid = paymentIsValid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGymId () {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean PaymentIsValid() {
        return paymentIsValid;
    }

    public void setPaymentIsValid(boolean paymentIsValid) {
        this.paymentIsValid = paymentIsValid;
    }

    public void setPaymentMethod(Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
