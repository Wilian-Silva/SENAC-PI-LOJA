
package DAO;

import BancoDados.ConexaoJDBC;
import Classes.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteDAO {
  
    public static boolean excluir(int id) { //metodo para excluir item da lista
        // Consulta SQL para deletar o cliente com base no ID
        String sql = "DELETE FROM cliente WHERE id = ?";

        // try-with-resources garante o fechamento automático da conexão
        try (ConexaoJDBC conn = new ConexaoJDBC()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define o valor do parâmetro (id do cliente a ser deletado)
            ps.setInt(1, id);

            // Executa a exclusão e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi deletada
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            return false;
        }
    }
   
    public static boolean cadastrar(Cliente c) {
        // Consulta SQL para inserir um novo filme
        String sql = "INSERT INTO cliente (nome, cpf, rg, endereco, bairro, cep, cidade, estado, telefone, email ) VALUES (?, ?, ?, ?, ? ,? ,? ,? ,?, ?)";

        // try-with-resources garante o fechamento automático da conexão
        try (ConexaoJDBC conn = new ConexaoJDBC()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define os valores dos parâmetros com os dados do objeto 'cliente'
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getRG());
            ps.setString(4, c.getEndereco());
            ps.setString(5, c.getBairro());
            ps.setString(6, c.getCep());
            ps.setString(7, c.getCidade());
            ps.setString(8, c.getEstado());
            ps.setString(9, c.getTelefone());
            ps.setString(10, c.getEmail());

            // Executa a inserção e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            
            return false;
        }

    }
    
    public static boolean atualizar(int id, Cliente c) {
        // Consulta SQL para inserir um novo filme
        String sql =  "UPDATE cliente SET nome = ?, cpf = ?, rg = ?, endereco = ?, bairro = ?, cep = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";

        // try-with-resources garante o fechamento automático da conexão
        try (ConexaoJDBC conn = new ConexaoJDBC()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define os valores dos parâmetros com os dados do objeto 'cliente'
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getRG());
            ps.setString(4, c.getEndereco());
            ps.setString(5, c.getBairro());
            ps.setString(6, c.getCep());
            ps.setString(7, c.getCidade());
            ps.setString(8, c.getEstado());
            ps.setString(9, c.getTelefone());
            ps.setString(10, c.getEmail());
            ps.setInt(11, id);

            // Executa a inserção e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            return false;
        }

    }
        
    public static List<Cliente> listar() {
        List<Cliente> lista = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            //Armazenamos a query SQL em uma string
            //String sql = "SELECT id, nome, cpf, endereco, telefone, especialidade, convenioId FROM Medico";
            String sql = "SELECT * FROM cliente";

            //Preparamos o comando para ser executado no banco
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

           while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setRG(rs.getString("rg"));
                c.setEndereco(rs.getString("endereco"));
                c.setBairro(rs.getString("bairro"));
                c.setCep(rs.getString("cep"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));

                lista.add(c);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar Clientes: " + se.getMessage());
        }

        return lista;
    }

    
}
