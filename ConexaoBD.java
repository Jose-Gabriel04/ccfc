package ccfc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/despachante";
    private static final String USER = "root";  // Altere para seu usuário
    private static final String PASSWORD = "sua_senha";  // Altere para sua senha

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}