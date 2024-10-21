package entities;

import java.util.Date;

public class Payment {

    private int id;
    private float amount;
    private Date paymentDate;

    private PaymentMethod paymentMethod;
    private enum PaymentMethod{
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
                "Cantidad: " + amount + "\n" +
                "Fecha: " + paymentDate + "\n" +
                "Metodo: " + paymentMethod + "\n"
                ;
    }

    public Payment() {
    }

    public Payment(int id, Member member, float amount, Date paymentDate, Payment.PaymentMethod paymentMethod) {
        this.id = id;
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

    public Payment.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
