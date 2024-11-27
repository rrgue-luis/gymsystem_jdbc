package enums.gym;

public enum GymStatus {
    OPERATIVE,
    MAINTENANCE,
    UNKNOWN;

    public static GymStatus fromString(String type) {
        try {
            return GymStatus.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de estado invalido, recibido: " + type + " Esperado: ('OPERATIVE', 'MAINTENANCE')");
        }
    }
}
