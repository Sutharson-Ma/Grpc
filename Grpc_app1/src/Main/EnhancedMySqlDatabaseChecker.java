package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EnhancedMySqlDatabaseChecker {
    private static final String EMPLOYEES_TABLE_DDL = "CREATE TABLE employees (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, role VARCHAR(100) NOT NULL, salary DOUBLE NOT NULL, district VARCHAR(100) NOT NULL, branch VARCHAR(100) NOT NULL)";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern HOST_PATTERN = Pattern.compile("^(localhost|(\\d{1,3}\\.){3}\\d{1,3})$");
    private static final Pattern DB_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]+$");

    public static Connection getValidatedConnection() throws Throwable {
        System.out.println("=== MySQL Database Connection Checker ===");
        String host = EnhancedMySqlDatabaseChecker.getValidHost();
        String port = EnhancedMySqlDatabaseChecker.getValidPort();
        String username = EnhancedMySqlDatabaseChecker.getInput("Enter username: ");
        String password = EnhancedMySqlDatabaseChecker.getInput("Enter password: ");
        String timeout = EnhancedMySqlDatabaseChecker.getValidTimeout();
        String baseUrl = "jdbc:mysql://" + host + ":" + port + "?connectTimeout=" + timeout;
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        props.setProperty("connectTimeout", timeout);
        System.out.println("\nAttempting to connect to MySQL server...");
        Connection conn = DriverManager.getConnection(baseUrl, props);
        try {
            String selectedDb;
            boolean isValid;
            System.out.println("\u2713 Connected to MySQL server");
            System.out.println("Server Version: " + conn.getMetaData().getDatabaseProductVersion());
            do {
                if ((selectedDb = EnhancedMySqlDatabaseChecker.databaseSelectionLoop(conn)) == null) {
                    System.out.println("Goodbye!");
                    throw new SQLException("User exited without selecting a valid database.");
                }
                System.out.println("\n\u2713 Selected database: " + selectedDb);
            } while (!(isValid = EnhancedMySqlDatabaseChecker.verifyOrCreateEmployeesTable(conn, selectedDb)));
            System.out.println("Table validation successful.");
            System.out.println("final dburl = jdbc:mysql://" + host + ":" + port + "/" + selectedDb);
            return conn;
        } catch (SQLException e) {
            if (conn != null && !conn.isClosed()) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error closing connection: " + closeEx.getMessage());
                }
            }
            throw e;
        }
    }

    private static String getValidHost() {
        String host;
        while (!HOST_PATTERN.matcher(host = EnhancedMySqlDatabaseChecker.getInput("Enter MySQL host (e.g., localhost or IP address): ")).matches()) {
            System.out.println("Invalid host. Please enter 'localhost' or a valid IP address (e.g., 127.0.0.1).");
        }
        return host;
    }

    private static String getValidPort() {
        String port;
        while (!(port = EnhancedMySqlDatabaseChecker.getInput("Enter MySQL port (default is 3306): ")).isEmpty()) {
            try {
                int portNum = Integer.parseInt(port);
                if (portNum >= 1 && portNum <= 65535) {
                    return port;
                }
                System.out.println("Port must be between 1 and 65535.");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Invalid port. Please enter a number.");
                continue;
            }
        }
        return "3306";
    }

    private static String getValidTimeout() {
        String timeout;
        while (!(timeout = EnhancedMySqlDatabaseChecker.getInput("Enter connection timeout in milliseconds (e.g., 3000): ")).isEmpty()) {
            try {
                int timeoutNum = Integer.parseInt(timeout);
                if (timeoutNum > 0) {
                    return timeout;
                }
                System.out.println("Timeout must be a positive number.");
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Invalid timeout. Please enter a number.");
                continue;
            }
        }
        return "3000";
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String databaseSelectionLoop(Connection conn) throws Throwable {
        block2: while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Enter database name manually");
            System.out.println("2. Select from available databases");
            System.out.println("3. Exit");
            System.out.print("Your choice (1-3): ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("3")) {
                return null;
            }
            if (choice.equals("1")) {
                String dbName;
                while (true) {
                    if ((dbName = EnhancedMySqlDatabaseChecker.getInput("Enter database name (or 'exit' to return to menu): ")).equalsIgnoreCase("exit")) continue block2;
                    if (!DB_NAME_PATTERN.matcher(dbName).matches()) {
                        System.out.println("Invalid database name. Use letters, numbers, or underscores only.");
                        continue;
                    }
                    if (EnhancedMySqlDatabaseChecker.checkDatabaseExists(conn.getMetaData(), dbName)) {
                        return dbName;
                    }
                    System.out.println("\u2717 Database '" + dbName + "' does not exist.");
                    if (EnhancedMySqlDatabaseChecker.getYesNoInput("Do you want to create the database '" + dbName + "'? (y/n): ")) break;
                    System.out.println("Please try another database name.");
                }
                EnhancedMySqlDatabaseChecker.createDatabase(conn, dbName);
                return dbName;
            }
            if (choice.equals("2")) {
                List<String> databases = EnhancedMySqlDatabaseChecker.listAllDatabases(conn.getMetaData());
                if (databases.isEmpty()) {
                    System.out.println("No databases found. Please create one manually.");
                    continue;
                }
                System.out.print("Enter the number of database to select (1-" + databases.size() + "): ");
                String input = scanner.nextLine().trim();
                try {
                    int dbNumber = Integer.parseInt(input);
                    if (dbNumber >= 1 && dbNumber <= databases.size()) {
                        return databases.get(dbNumber - 1);
                    }
                    System.out.println("Invalid selection. Please enter a number between 1 and " + databases.size() + ".");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
                continue;
            }
            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }

    private static boolean checkDatabaseExists(DatabaseMetaData meta, String dbName) throws SQLException {
        try (ResultSet rs = meta.getCatalogs()) {
            while (rs.next()) {
                String catalog = rs.getString(1).toLowerCase();
                if (catalog.equals(dbName.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    }

    private static List<String> listAllDatabases(DatabaseMetaData meta) throws Throwable {
        ArrayList<String> databases = new ArrayList<String>();
        try (ResultSet rs = meta.getCatalogs()) {
            while (rs.next()) {
                databases.add(rs.getString(1));
            }
        }
        if (databases.isEmpty()) {
            System.out.println("No databases found on this server.");
        } else {
            System.out.println("\nAvailable databases:");
            int i = 0;
            while (i < databases.size()) {
                System.out.println(i + 1 + ". " + databases.get(i));
                ++i;
            }
            System.out.println("Total: " + databases.size() + " databases");
        }
        return databases;
    }

    private static void createDatabase(Connection conn, String dbName) throws Throwable {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + dbName);
            System.out.println("\u2713 Database '" + dbName + "' created successfully.");
        }
    }

    private static boolean verifyOrCreateEmployeesTable(Connection conn, String catalog) throws Throwable {
        conn.setCatalog(catalog);
        if (!EnhancedMySqlDatabaseChecker.tableExists(conn, "employees")) {
            System.out.println("\n\u2717 The 'employees' table does not exist in database: " + catalog);
            if (EnhancedMySqlDatabaseChecker.getYesNoInput("Do you want to create it now? (y/n): ")) {
                EnhancedMySqlDatabaseChecker.createEmployeesTable(conn);
                System.out.println("\u2713 Table structure matches expected format");
                return true;
            }
            return false;
        }
        System.out.println("\n\u2713 'employees' table exists");
        TableValidationResult validation = EnhancedMySqlDatabaseChecker.verifyTableStructure(conn, catalog, "employees");
        if (!validation.isValid()) {
            System.out.println("\u26a0 The table structure doesn't match the required format");
            System.out.println("Validation errors:");
            for (String error : validation.getErrors()) {
                System.out.println(" - " + error);
            }
            if (EnhancedMySqlDatabaseChecker.getYesNoInput("Do you want to drop and recreate it? (y/n): ")) {
                EnhancedMySqlDatabaseChecker.dropTable(conn, "employees");
                EnhancedMySqlDatabaseChecker.createEmployeesTable(conn);
                System.out.println("\u2713 Table structure matches expected format");
                return true;
            }
            return false;
        }
        System.out.println("\u2713 Table structure matches expected format");
        return true;
    }

    private static boolean tableExists(Connection conn, String tableName) throws Throwable {
        DatabaseMetaData meta = conn.getMetaData();
        try (ResultSet rs = meta.getTables(conn.getCatalog(), null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }

    private static TableValidationResult verifyTableStructure(Connection conn, String catalog, String tableName) throws Throwable {
        LinkedHashMap<String, String> expectedColumns = new LinkedHashMap<String, String>();
        expectedColumns.put("id", "BIGINT");
        expectedColumns.put("name", "VARCHAR(100)");
        expectedColumns.put("role", "VARCHAR(100)");
        expectedColumns.put("salary", "DOUBLE");
        expectedColumns.put("district", "VARCHAR(100)");
        expectedColumns.put("branch", "VARCHAR(100)");
        DatabaseMetaData meta = conn.getMetaData();
        TableValidationResult result = new TableValidationResult();
        try (ResultSet rs = meta.getColumns(catalog, null, tableName, null)) {
            LinkedHashMap<String, String> actualColumns = new LinkedHashMap<String, String>();
            while (rs.next()) {
                String colName = rs.getString("COLUMN_NAME").toLowerCase();
                String colType = rs.getString("TYPE_NAME").toUpperCase();
                int charLength = rs.getInt("COLUMN_SIZE");
                if (colType.equals("VARCHAR")) {
                    colType = colType + "(" + charLength + ")";
                }
                actualColumns.put(colName, colType);
            }
            System.out.println("Detected columns: " + String.valueOf(actualColumns.keySet()));
            for (Map.Entry<String, String> expected : expectedColumns.entrySet()) {
                if (!actualColumns.containsKey(expected.getKey())) {
                    result.addError("Missing column: " + expected.getKey());
                    continue;
                }
                if (actualColumns.get(expected.getKey()).equalsIgnoreCase(expected.getValue())) continue;
                result.addError("Type mismatch for column " + expected.getKey() + ". Expected: " + expected.getValue() + ", Actual: " + actualColumns.get(expected.getKey()));
            }
            for (String actualCol : actualColumns.keySet()) {
                if (expectedColumns.containsKey(actualCol)) continue;
                result.addError("Extra column found: " + actualCol);
            }
            if (actualColumns.size() != expectedColumns.size()) {
                result.addError("Column count mismatch. Expected: " + expectedColumns.size() + ", Actual: " + actualColumns.size());
            }
        }
        return result;
    }

    private static void createEmployeesTable(Connection conn) throws Throwable {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(EMPLOYEES_TABLE_DDL);
            System.out.println("\u2713 'employees' table created successfully");
        }
    }

    private static void dropTable(Connection conn, String tableName) throws Throwable {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE " + tableName);
            System.out.println("\u2713 Table '" + tableName + "' dropped successfully");
        }
    }

    private static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    private static class TableValidationResult {
        private boolean valid = true;
        private List<String> errors = new ArrayList<String>();

        private TableValidationResult() {
        }

        public boolean isValid() {
            return this.valid && this.errors.isEmpty();
        }

        public List<String> getErrors() {
            return this.errors;
        }

        public void addError(String error) {
            this.valid = false;
            this.errors.add(error);
        }
    }
}