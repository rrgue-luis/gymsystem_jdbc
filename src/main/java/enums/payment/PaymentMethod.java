package enums.payment;

public enum PaymentMethod {
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
