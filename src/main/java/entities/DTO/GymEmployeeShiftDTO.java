package entities.DTO;

import entities.Employee;

public class GymEmployeeShiftDTO {

    final int gymId;
    final int employeeId;

    final String employeeName;
    final String gymName;
    final EmployeeShift employeeShift;
    public enum EmployeeShift {

        MORNING,
        AFTERNOON,
        NIGHT;

        public static Employee.EmployeeShift fromString(String type) {
            try {
                return Employee.EmployeeShift.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de horario inválido, recibido: " + type + " Esperado: ('MORNING', 'AFTERNOON', 'NIGHT')");
            }

        }
    }

    public int getGymId() {
        return gymId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getGymName() {
        return gymName;
    }

    public EmployeeShift getEmployeeShift() {
        return employeeShift;
    }

    public GymEmployeeShiftDTO(int gymId, int employeeId, String employeeName, String gymName, EmployeeShift employeeShift) {
        this.gymId = gymId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gymName = gymName;
        this.employeeShift = employeeShift;
    }
}


