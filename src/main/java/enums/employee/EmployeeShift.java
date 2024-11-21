package enums.employee;

import entities.Employee;

public enum EmployeeShift {

    MORNING,
    AFTERNOON,
    NIGHT;

    public static EmployeeShift fromString(String type) {
        try {
            return EmployeeShift.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de turno inválido, recibido: " + type + " Esperado: ('MORNING', 'AFTERNOON, 'NIGHT')");
        }
    }

    EmployeeShift() {}
}
