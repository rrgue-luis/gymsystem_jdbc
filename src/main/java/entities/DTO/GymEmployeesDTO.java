package entities.DTO;

public class GymEmployeesDTO {
        final int gymId;
        final int employeeId;

        final String gymName;
        final String employeeName;


        public GymEmployeesDTO(int gymId, int employeeId, String gymName, String employeeName) {
            this.gymId = gymId;
            this.employeeId = employeeId;
            this.gymName = gymName;
            this.employeeName = employeeName;
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

}
