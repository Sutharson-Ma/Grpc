package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.combined.grpc.*;
import com.example.combined.grpc.EmployeeServiceGrpc;

import io.grpc.stub.StreamObserver;

// EmployeeServiceImpl implements the gRPC EmployeeService on App1, handling database operations for employee management
public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {
    // Database connection for executing SQL queries
    private final Connection dbConnection;

    // Constructor initializes the database connection
    public EmployeeServiceImpl(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Creates a new employee in the database
    @Override
    public void createEmployee(CreateEmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        System.out.println("CREATE EMPLOYEE called on App1");
        // SQL query to insert a new employee record
        String sql = "INSERT INTO employees (name, role, salary, district, branch) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters for the prepared statement from the request
            statement.setString(1, request.getName());
            statement.setString(2, request.getRole());
            statement.setDouble(3, request.getSalary());
            statement.setString(4, request.getDistrict());
            statement.setString(5, request.getBranch());
            
            // Execute the insert query and check if a row was affected
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows == 0) {
                // If no rows were inserted, send an error to App2
                responseObserver.onError(new RuntimeException("App1 failed to create employee"));
                return;
            }
            
            // Retrieve the auto-generated ID for the new employee
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Build an Employee object with the generated ID and request data
                    Employee employee = Employee.newBuilder()
                            .setId(generatedKeys.getLong(1))
                            .setName(request.getName())
                            .setRole(request.getRole())
                            .setSalary(request.getSalary())
                            .setDistrict(request.getDistrict())
                            .setBranch(request.getBranch())
                            .build();
                    
                    // Wrap the Employee in an EmployeeResponse and send it to App2
                    EmployeeResponse response = EmployeeResponse.newBuilder()
                            .setEmployee(employee)
                            .build();
                    
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                } else {
                    // If no ID was generated, send an error to App2
                    responseObserver.onError(new RuntimeException("App1 failed to get generated ID"));
                }
            }
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves an employee by their ID
    @Override
    public void getEmployeeById(EmployeeByIdRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        System.out.println("GET EMPLOYEE BY ID called on App1 for ID: " + request.getId());
        // SQL query to select an employee by ID
        String sql = "SELECT * FROM employees WHERE id = ?";
        
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            // Set the ID parameter
            statement.setLong(1, request.getId());
            
            // Execute the query and process the result
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Build an Employee object from the result set
                    Employee employee = buildEmployeeFromResultSet(rs);
                    // Send the Employee wrapped in an EmployeeResponse to App2
                    responseObserver.onNext(EmployeeResponse.newBuilder().setEmployee(employee).build());
                    responseObserver.onCompleted();
                } else {
                    // If no employee is found, send an error to App2
                    responseObserver.onError(new RuntimeException("Employee not found on App1"));
                }
            }
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves employees by their role
    @Override
    public void getEmployeesByRole(EmployeeByRoleRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY ROLE called on App1 for role: " + request.getRole());
        // Use helper method to execute query with a single string parameter
        executeQueryWithParam("SELECT * FROM employees WHERE role = ?", 
                             request.getRole(), 
                             responseObserver);
    }

    // Retrieves employees by their exact salary
    @Override
    public void getEmployeesBySalary(EmployeeBySalaryRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY SALARY called on App1 for salary: " + request.getSalary());
        // Use helper method to execute query with a single double parameter
        executeQueryWithParam("SELECT * FROM employees WHERE salary = ?", 
                             request.getSalary(), 
                             responseObserver);
    }

    // Retrieves employees by their district
    @Override
    public void getEmployeesByDistrict(EmployeeByDistrictRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY DISTRICT called on App1 for district: " + request.getDistrict());
        // Use helper method to execute query with a single string parameter
        executeQueryWithParam("SELECT * FROM employees WHERE district = ?", 
                             request.getDistrict(), 
                             responseObserver);
    }

    // Retrieves employees by their branch
    @Override
    public void getEmployeesByBranch(EmployeeByBranchRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY BRANCH called on App1 for branch: " + request.getBranch());
        // Use helper method to execute query with a single string parameter
        executeQueryWithParam("SELECT * FROM employees WHERE branch = ?", 
                             request.getBranch(), 
                             responseObserver);
    }

    // Retrieves all employees
    @Override
    public void getAllEmployees(Empty request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET ALL EMPLOYEES called on App1");
        try (Statement stmt = dbConnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            // Collect all employees in a list
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                employees.add(buildEmployeeFromResultSet(rs));
            }
            // Send the list wrapped in an EmployeesResponse to App2
            responseObserver.onNext(EmployeesResponse.newBuilder().addAllEmployees(employees).build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves employees by role and branch
    @Override
    public void getEmployeesByRoleAndBranch(EmployeeByRoleAndBranchRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY ROLE AND BRANCH called on App1");
        // SQL query to select employees by role and branch
        String sql = "SELECT * FROM employees WHERE role = ? AND branch = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set parameters for role and branch
            stmt.setString(1, request.getRole());
            stmt.setString(2, request.getBranch());
            // Execute query and send response
            sendEmployeeListResponse(stmt, responseObserver);
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves employees by district and branch
    @Override
    public void getEmployeesByDistrictAndBranch(EmployeeByDistrictAndBranchRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY DISTRICT AND BRANCH called on App1");
        // SQL query to select employees by district and branch
        String sql = "SELECT * FROM employees WHERE district = ? AND branch = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set parameters for district and branch
            stmt.setString(1, request.getDistrict());
            stmt.setString(2, request.getBranch());
            // Execute query and send response
            sendEmployeeListResponse(stmt, responseObserver);
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves employees with salary greater than a specified amount
    @Override
    public void getEmployeesWithSalaryGreaterThan(EmployeeByMinSalaryRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES WITH SALARY > " + request.getMinSalary() + " called on App1");
        // Use helper method to execute query with a single double parameter
        executeQueryWithParam("SELECT * FROM employees WHERE salary > ?", 
                             request.getMinSalary(), 
                             responseObserver);
    }

    // Retrieves employees with salary within a specified range
    @Override
    public void getEmployeesWithSalaryBetween(EmployeeBySalaryRangeRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES WITH SALARY BETWEEN " + request.getMinSalary() + " AND " + request.getMaxSalary() + " called on App1");
        // SQL query to select employees by salary range
        String sql = "SELECT * FROM employees WHERE salary BETWEEN ? AND ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set parameters for min and max salary
            stmt.setDouble(1, request.getMinSalary());
            stmt.setDouble(2, request.getMaxSalary());
            // Execute query and send response
            sendEmployeeListResponse(stmt, responseObserver);
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves employees by role with a minimum salary
    @Override
    public void getEmployeesByRoleAndMinSalary(EmployeeByRoleAndMinSalaryRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY ROLE AND MIN SALARY called on App1");
        // SQL query to select employees by role and minimum salary
        String sql = "SELECT * FROM employees WHERE role = ? AND salary >= ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set parameters for role and minimum salary
            stmt.setString(1, request.getRole());
            stmt.setDouble(2, request.getMinSalary());
            // Execute query and send response
            sendEmployeeListResponse(stmt, responseObserver);
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves employees by district, ordered by salary
    @Override
    public void getEmployeesByDistrictOrderedBySalary(EmployeeByDistrictOrderedRequest request, StreamObserver<EmployeesResponse> responseObserver) {
        System.out.println("GET EMPLOYEES BY DISTRICT ORDERED BY SALARY called on App1");
        // SQL query to select employees by district, ordered by salary
        String sql = "SELECT * FROM employees WHERE district = ? ORDER BY salary";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set parameter for district
            stmt.setString(1, request.getDistrict());
            // Execute query and send response
            sendEmployeeListResponse(stmt, responseObserver);
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Retrieves names and roles of employees by branch
    @Override
    public void getEmployeeNamesAndRolesByBranch(EmployeeNamesByBranchRequest request, StreamObserver<EmployeeNamesResponse> responseObserver) {
        System.out.println("GET EMPLOYEE NAMES AND ROLES BY BRANCH called on App1");
        // SQL query to select name and role by branch
        String sql = "SELECT name, role FROM employees WHERE branch = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set parameter for branch
            stmt.setString(1, request.getBranch());
            
            // Collect name and role pairs in a list
            List<EmployeeNameRole> nameRoles = new ArrayList<>();
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    nameRoles.add(EmployeeNameRole.newBuilder()
                            .setName(rs.getString("name"))
                            .setRole(rs.getString("role"))
                            .build());
                }
            }
            
            // Send the list wrapped in an EmployeeNamesResponse to App2
            responseObserver.onNext(EmployeeNamesResponse.newBuilder()
                    .addAllEmployees(nameRoles)
                    .build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Helper method to build an Employee object from a ResultSet
    private Employee buildEmployeeFromResultSet(ResultSet rs) throws SQLException {
        return Employee.newBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setRole(rs.getString("role"))
                .setSalary(rs.getDouble("salary"))
                .setDistrict(rs.getString("district"))
                .setBranch(rs.getString("branch"))
                .build();
    }

    // Helper method to execute a query with a single parameter (string or double)
    private void executeQueryWithParam(String sql, Object param, StreamObserver<EmployeesResponse> responseObserver) {
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            // Set the parameter based on its type
            if (param instanceof String) {
                stmt.setString(1, (String) param);
            } else if (param instanceof Double) {
                stmt.setDouble(1, (Double) param);
            }
            // Execute query and send response
            sendEmployeeListResponse(stmt, responseObserver);
        } catch (SQLException e) {
            // Log and send SQL errors to App2
            System.err.println("App1 database error: " + e.getMessage());
            responseObserver.onError(e);
        }
    }

    // Helper method to execute a query and send a list of employees
    private void sendEmployeeListResponse(PreparedStatement stmt, StreamObserver<EmployeesResponse> responseObserver) throws SQLException {
        // Collect employees in a list
        List<Employee> employees = new ArrayList<>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                employees.add(buildEmployeeFromResultSet(rs));
            }
        }
        // Send the list wrapped in an EmployeesResponse to App2
        responseObserver.onNext(EmployeesResponse.newBuilder()
                .addAllEmployees(employees)
                .build());
        responseObserver.onCompleted();
    }
}