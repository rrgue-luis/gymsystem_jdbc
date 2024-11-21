package enums.member;

public enum MembershipType {
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

    MembershipType() {
    }
}
