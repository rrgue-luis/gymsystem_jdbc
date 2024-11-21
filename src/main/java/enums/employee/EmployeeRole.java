package enums.employee;


public enum EmployeeRole {

    BOSS,
    MANAGER,
    TRAINER,
    EMPLOYEE;

    public static EmployeeRole fromString(String type) {
        try {
            return EmployeeRole.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de rol inválido, recibido: " + type + " Esperado: ('BOSS', 'MANAGER, 'TRAINER', 'EMPLOYEE')");
        }
    }

    EmployeeRole() {}
}
