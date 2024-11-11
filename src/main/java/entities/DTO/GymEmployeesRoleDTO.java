package entities.DTO;

import entities.Employee;

public class GymEmployeesRoleDTO {

    final int gymId;
    final int employeeId;

    final String gymName;
    final String employeeName;

    final EmployeeRole employeeRole;

    public enum EmployeeRole {

        BOSS,
        MANAGER,
        TRAINER,
        EMPLOYEE;

        public static Employee.EmployeeRole fromString(String type) {
            try {
                return Employee.EmployeeRole.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de rol inválido, recibido: " + type + " Esperado: ('BOSS', 'MANAGER, 'TRAINER', 'EMPLOYEE')");
            }
        }
    }

    public int getGymId() {
        return gymId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getGymName() {
        return gymName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public GymEmployeesRoleDTO(int gymId, int employeeId, String gymName, String employeeName, EmployeeRole employeeRole) {
        this.gymId = gymId;
        this.employeeId = employeeId;
        this.gymName = gymName;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
    }
}
