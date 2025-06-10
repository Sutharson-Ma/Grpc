package Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.example.combined.grpc.*;
import com.example.combined.grpc.EmployeeServiceGrpc;

// EmployeeRequest is a gRPC App2 for interacting with the EmployeeService on App1, providing methods to perform CRUD and query operations
public class EmployeeRequest {
    // gRPC channel for communication with App1
    private final ManagedChannel channel;
    // Blocking stub for synchronous gRPC calls to EmployeeService
    private final EmployeeServiceGrpc.EmployeeServiceBlockingStub blockingStub;
    // Scanner for reading user input from the console
    private final Scanner scanner;

    // Constructor that takes host and port, using plaintext communication
    public EmployeeRequest(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    // Constructor that takes a ManagedChannelBuilder to create the channel and stub
    public EmployeeRequest(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = EmployeeServiceGrpc.newBlockingStub(channel);
        scanner = new Scanner(System.in);
    }

    // Shuts down the gRPC channel gracefully
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // Formats an Employee object into a readable string
    private String formatEmployee(Employee employee) {
        return String.format(
            "ID: %d\nName: %s\nRole: %s\nSalary: %.2f\nDistrict: %s\nBranch: %s\n",
            employee.getId(),
            employee.getName(),
            employee.getRole(),
            employee.getSalary(),
            employee.getDistrict(),
            employee.getBranch()
        );
    }

    // Formats an EmployeeNameRole object into a readable string
    private String formatNameRole(EmployeeNameRole nameRole) {
        return String.format("Name: %-20s Role: %s", nameRole.getName(), nameRole.getRole());
    }

    // Creates a new employee with the provided details
    public void createEmployee(String name, String role, double salary, String district, String branch) {
        System.out.println("\n=== Creating Employee ===");
        // Build the gRPC request
        CreateEmployeeRequest request = CreateEmployeeRequest.newBuilder()
                .setName(name)
                .setRole(role)
                .setSalary(salary)
                .setDistrict(district)
                .setBranch(branch)
                .build();

        try {
            // Call App1 to create the employee
            EmployeeResponse response = blockingStub.createEmployee(request);
            // Display the created employee's details
            System.out.println("Created successfully on App1:\n" + formatEmployee(response.getEmployee()));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 create failed: " + e.getStatus());
        }
    }

    // Retrieves an employee by their ID
    public void getEmployeeById(long id) {
        System.out.println("\n=== Getting Employee ID: " + id + " ===");
        // Build the gRPC request
        EmployeeByIdRequest request = EmployeeByIdRequest.newBuilder()
                .setId(id)
                .build();

        try {
            // Call App1 to get the employee
            EmployeeResponse response = blockingStub.getEmployeeById(request);
            // Display the employee's details
            System.out.println("Employee details from App1:\n" + formatEmployee(response.getEmployee()));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 get failed: " + e.getStatus());
        }
    }

    // Retrieves employees by their role
    public void getEmployeesByRole(String role) {
        System.out.println("\n=== Employees with Role: " + role + " ===");
        // Build the gRPC request
        EmployeeByRoleRequest request = EmployeeByRoleRequest.newBuilder()
                .setRole(role)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesByRole(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves all employees
    public void getAllEmployees() {
        System.out.println("\n=== All Employees ===");
        // Build an empty gRPC request
        Empty request = Empty.newBuilder().build();

        try {
            // Call App1 to get all employees
            EmployeesResponse response = blockingStub.getAllEmployees(request);
            // Display the total number of employees
            System.out.println("Total employees from App1: " + response.getEmployeesCount());
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees by their exact salary
    public void getEmployeesBySalary(double salary) {
        System.out.println("\n=== Employees with Salary: " + salary + " ===");
        // Build the gRPC request
        EmployeeBySalaryRequest request = EmployeeBySalaryRequest.newBuilder()
                .setSalary(salary)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesBySalary(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees by their district
    public void getEmployeesByDistrict(String district) {
        System.out.println("\n=== Employees in District: " + district + " ===");
        // Build the gRPC request
        EmployeeByDistrictRequest request = EmployeeByDistrictRequest.newBuilder()
                .setDistrict(district)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesByDistrict(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees by their branch
    public void getEmployeesByBranch(String branch) {
        System.out.println("\n=== Employees in Branch: " + branch + " ===");
        // Build the gRPC request
        EmployeeByBranchRequest request = EmployeeByBranchRequest.newBuilder()
                .setBranch(branch)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesByBranch(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees by role and branch
    public void getEmployeesByRoleAndBranch(String role, String branch) {
        System.out.println("\n=== Employees with Role: " + role + " in Branch: " + branch + " ===");
        // Build the gRPC request
        EmployeeByRoleAndBranchRequest request = EmployeeByRoleAndBranchRequest.newBuilder()
                .setRole(role)
                .setBranch(branch)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesByRoleAndBranch(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees with salary greater than a specified amount
    public void getEmployeesWithSalaryGreaterThan(double minSalary) {
        System.out.println("\n=== Employees with Salary > " + minSalary + " ===");
        // Build the gRPC request
        EmployeeByMinSalaryRequest request = EmployeeByMinSalaryRequest.newBuilder()
                .setMinSalary(minSalary)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesWithSalaryGreaterThan(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees with salary within a specified range
    public void getEmployeesWithSalaryBetween(double minSalary, double maxSalary) {
        System.out.println("\n=== Employees with Salary Between " + minSalary + " and " + maxSalary + " ===");
        // Build the gRPC request
        EmployeeBySalaryRangeRequest request = EmployeeBySalaryRangeRequest.newBuilder()
                .setMinSalary(minSalary)
                .setMaxSalary(maxSalary)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesWithSalaryBetween(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees by role with a minimum salary
    public void getEmployeesByRoleAndMinSalary(String role, double minSalary) {
        System.out.println("\n=== Employees with Role: " + role + " and Salary >= " + minSalary + " ===");
        // Build the gRPC request
        EmployeeByRoleAndMinSalaryRequest request = EmployeeByRoleAndMinSalaryRequest.newBuilder()
                .setRole(role)
                .setMinSalary(minSalary)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesByRoleAndMinSalary(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves employees by district, ordered by salary
    public void getEmployeesByDistrictOrderedBySalary(String district) {
        System.out.println("\n=== Employees in District: " + district + " (Ordered by Salary) ===");
        // Build the gRPC request
        EmployeeByDistrictOrderedRequest request = EmployeeByDistrictOrderedRequest.newBuilder()
                .setDistrict(district)
                .build();

        try {
            // Call App1 to get employees
            EmployeesResponse response = blockingStub.getEmployeesByDistrictOrderedBySalary(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each employee's details
            response.getEmployeesList().forEach(emp -> 
                System.out.println(formatEmployee(emp)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Retrieves names and roles of employees by branch
    public void getEmployeeNamesAndRolesByBranch(String branch) {
        System.out.println("\n=== Names and Roles in Branch: " + branch + " ===");
        // Build the gRPC request
        EmployeeNamesByBranchRequest request = EmployeeNamesByBranchRequest.newBuilder()
                .setBranch(branch)
                .build();

        try {
            // Call App1 to get names and roles
            EmployeeNamesResponse response = blockingStub.getEmployeeNamesAndRolesByBranch(request);
            // Display the number of results
            System.out.println("Results (" + response.getEmployeesCount() + "):");
            // Display each name and role pair
            response.getEmployeesList().forEach(er -> 
                System.out.println(formatNameRole(er)));
        } catch (StatusRuntimeException e) {
            // Handle gRPC errors
            System.err.println("App2 query failed: " + e.getStatus());
        }
    }

    // Displays the menu of available operations
    public void displayMenu() {
        System.out.println("\n==== Employee Service App2 ====");
        System.out.println("1. Create Employee");
        System.out.println("2. Get Employee by ID");
        System.out.println("3. Get Employees by Role");
        System.out.println("4. Get Employees by Salary");
        System.out.println("5. Get Employees by District");
        System.out.println("6. Get Employees by Branch");
        System.out.println("7. Get All Employees");
        System.out.println("8. Get Employees by Role and Branch");
        System.out.println("9. Get Employees with Salary >");
        System.out.println("10. Get Employees with Salary Between");
        System.out.println("11. Get Employees by Role and Min Salary");
        System.out.println("12. Get Employees by District Ordered by Salary");
        System.out.println("13. Get Employee Names and Roles by Branch");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Handles user input to execute the chosen operation
    public void handleUserChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    // Prompt for employee details
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter role: ");
                    String role = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter district: ");
                    String district = scanner.nextLine();
                    System.out.print("Enter branch: ");
                    String branch = scanner.nextLine();
                    // Create employee
                    createEmployee(name, role, salary, district, branch);
                    break;
                    
                case 2:
                    // Prompt for employee ID
                    System.out.print("Enter employee ID: ");
                    long id = Long.parseLong(scanner.nextLine());
                    // Get employee by ID
                    getEmployeeById(id);
                    break;
                    
                case 3:
                    // Prompt for role
                    System.out.print("Enter role: ");
                    String roleQuery = scanner.nextLine();
                    // Get employees by role
                    getEmployeesByRole(roleQuery);
                    break;
                    
                case 4:
                    // Prompt for salary
                    System.out.print("Enter salary: ");
                    double salaryQuery = Double.parseDouble(scanner.nextLine());
                    // Get employees by salary
                    getEmployeesBySalary(salaryQuery);
                    break;
                    
                case 5:
                    // Prompt for district
                    System.out.print("Enter district: ");
                    String districtQuery = scanner.nextLine();
                    // Get employees by district
                    getEmployeesByDistrict(districtQuery);
                    break;
                    
                case 6:
                    // Prompt for branch
                    System.out.print("Enter branch: ");
                    String branchQuery = scanner.nextLine();
                    // Get employees by branch
                    getEmployeesByBranch(branchQuery);
                    break;
                    
                case 7:
                    // Get all employees
                    getAllEmployees();
                    break;
                    
                case 8:
                    // Prompt for role and branch
                    System.out.print("Enter role: ");
                    String roleBranch = scanner.nextLine();
                    System.out.print("Enter branch: ");
                    String branchRole = scanner.nextLine();
                    // Get employees by role and branch
                    getEmployeesByRoleAndBranch(roleBranch, branchRole);
                    break;
                    
                case 9:
                    // Prompt for minimum salary
                    System.out.print("Enter minimum salary: ");
                    double minSalary = Double.parseDouble(scanner.nextLine());
                    // Get employees with salary greater than
                    getEmployeesWithSalaryGreaterThan(minSalary);
                    break;
                    
                case 10:
                    // Prompt for salary range
                    System.out.print("Enter minimum salary: ");
                    double min = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter maximum salary: ");
                    double max = Double.parseDouble(scanner.nextLine());
                    // Get employees with salary between
                    getEmployeesWithSalaryBetween(min, max);
                    break;
                    
                case 11:
                    // Prompt for role and minimum salary
                    System.out.print("Enter role: ");
                    String roleMin = scanner.nextLine();
                    System.out.print("Enter minimum salary: ");
                    double minSal = Double.parseDouble(scanner.nextLine());
                    // Get employees by role and minimum salary
                    getEmployeesByRoleAndMinSalary(roleMin, minSal);
                    break;
                    
                case 12:
                    // Prompt for district
                    System.out.print("Enter district: ");
                    String districtOrder = scanner.nextLine();
                    // Get employees by district, ordered by salary
                    getEmployeesByDistrictOrderedBySalary(districtOrder);
                    break;
                    
                case 13:
                    // Prompt for branch
                    System.out.print("Enter branch: ");
                    String branchNames = scanner.nextLine();
                    // Get employee names and roles by branch
                    getEmployeeNamesAndRolesByBranch(branchNames);
                    break;
                    
                case 0:
                    // Exit the menu
                    System.out.println("Exiting...");
                    break;
                    
                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            // Handle invalid numeric inputs
            System.err.println("App2 invalid input format: Please enter numbers where required.");
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("App2 error: " + e.getMessage());
        }
    }
}