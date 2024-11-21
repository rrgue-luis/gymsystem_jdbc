package enums.employee;

import entities.Employee;

public enum EmployeeStatus {
    ACTIVE,
    INACTIVE,
    VACATIONS;

    public static EmployeeStatus fromString(String type) {
        try {
            return EmployeeStatus.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de estado inválido, recibido: " + type + " Esperado: ('ACTIVE', 'INACTIVE, 'VACATIONS')");
        }
    }

    EmployeeStatus() {}

}
