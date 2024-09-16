package ccfc;

import java.sql.*;
import java.util.ArrayList;

public class GerenciarClientes {
    
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, telefone, servicos_realizados) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getServicosRealizados());

            stmt.executeUpdate();
            System.out.println("Cliente adicionado ao banco de dados com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente ao banco de dados: " + e.getMessage());
        }
    }

    public void listarClientes() {
        String sql = "SELECT * FROM clientes";
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String servicosRealizados = rs.getString("servicos_realizados");

                Cliente cliente = new Cliente(id, nome, telefone, servicosRealizados);
                clientes.add(cliente);
            }

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
            } else {
                for (Cliente cliente : clientes) {
                    System.out.println(cliente);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
    }

    public void atualizarCliente(int id, String novoTelefone, String novosServicos) {
        String sql = "UPDATE clientes SET telefone = ?, servicos_realizados = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoTelefone);
            stmt.setString(2, novosServicos);
            stmt.setInt(3, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void removerCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente: " + e.getMessage());
        }
    }
}