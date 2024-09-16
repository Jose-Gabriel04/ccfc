package ccfc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Caixa {
    private double saldo;

    // Registrar um débito
    public void registrarDebito(double valor) {
        String sql = "INSERT INTO caixa (tipo, valor) VALUES ('debito', ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDouble(1, valor);
            stmt.executeUpdate();
            saldo -= valor;
            System.out.println("Débito registrado: R$" + valor);

        } catch (SQLException e) {
            System.out.println("Erro ao registrar débito: " + e.getMessage());
        }
    }

    // Registrar um crédito
    public void registrarCredito(double valor) {
        String sql = "INSERT INTO caixa (tipo, valor) VALUES ('credito', ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDouble(1, valor);
            stmt.executeUpdate();
            saldo += valor;
            System.out.println("Crédito registrado: R$" + valor);

        } catch (SQLException e) {
            System.out.println("Erro ao registrar crédito: " + e.getMessage());
        }
    }

    // Exibir o saldo atual
    public void exibirSaldo() {
        String sql = "SELECT SUM(CASE WHEN tipo = 'credito' THEN valor ELSE -valor END) AS saldo FROM caixa";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
            System.out.println("Saldo atual: R$" + saldo);

        } catch (SQLException e) {
            System.out.println("Erro ao exibir saldo: " + e.getMessage());
        }
    }

    // Somatório mensal de débitos e créditos
    public void somatorioMensal() {
        String sqlDebito = "SELECT SUM(valor) AS totalDebitos FROM caixa WHERE tipo = 'debito'";
        String sqlCredito = "SELECT SUM(valor) AS totalCreditos FROM caixa WHERE tipo = 'credito'";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmtDebito = conexao.prepareStatement(sqlDebito);
             PreparedStatement stmtCredito = conexao.prepareStatement(sqlCredito);
             ResultSet rsDebito = stmtDebito.executeQuery();
             ResultSet rsCredito = stmtCredito.executeQuery()) {

            double totalDebitos = rsDebito.next() ? rsDebito.getDouble("totalDebitos") : 0;
            double totalCreditos = rsCredito.next() ? rsCredito.getDouble("totalCreditos") : 0;

            System.out.println("Somatório mensal de débitos: R$" + totalDebitos);
            System.out.println("Somatório mensal de créditos: R$" + totalCreditos);

        } catch (SQLException e) {
            System.out.println("Erro ao calcular somatório mensal: " + e.getMessage());
        }
    }
}