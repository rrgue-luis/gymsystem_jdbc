package entities;

import java.time.LocalDate;
import java.util.Date;

public class Payment {

    private int id;
    private float amount;
    private LocalDate paymentDate;

    private PaymentMethod paymentMethod;
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
                "Cantidad: " + amount + "\n" +
                "Fecha: " + paymentDate + "\n" +
                "Metodo: " + paymentMethod + "\n"
                ;
    }

    public Payment() {
    }

    public Payment(int id, Member member, float amount, LocalDate paymentDate, Payment.PaymentMethod paymentMethod) {
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
