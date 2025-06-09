
package DAO;

import BancoDados.ConexaoJDBC;
import Classes.Produto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProdutoDAO {
  
    public static boolean excluir(int id) { //metodo para excluir item da lista
        // Consulta SQL para deletar o cliente com base no ID
        String sql = "DELETE FROM produto WHERE id = ?";

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
   
    public static boolean cadastrar(Produto p) {
        // Consulta SQL para inserir um novo filme
        String sql = "INSERT INTO vendas (data_venda, total_venda, cliente_id) VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? )";

        // try-with-resources garante o fechamento automático da conexão
        try (ConexaoJDBC conn = new ConexaoJDBC()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define os valores dos parâmetros com os dados do objeto 'cliente'
            ps.setString(1, p.getCodBarras());
            ps.setString(2, p.getDescricao());
            ps.setString(3, p.getCategoria());
            ps.setString(4, p.getUndMedida());
            ps.setDouble(5, p.getCusto());
            ps.setDouble(6, p.getPrecoVenda());
            ps.setString(7, p.getStatus());
            ps.setInt(8, p.getSaldo());
            ps.setBoolean(9, p.isControlaSaldo());       

            // Executa a inserção e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            return false;
        }

    }
    
    public static boolean atualizar(int id, Produto p) {
        // Consulta SQL para inserir um novo filme
        String sql =  "UPDATE produto SET cod_barras = ?, descricao = ?, categoria = ?, und_medida = ?, custo = ?, preco_venda = ?, status = ?, saldo = ?, controla_saldo = ? WHERE id = ?";

        // try-with-resources garante o fechamento automático da conexão
        try (ConexaoJDBC conn = new ConexaoJDBC()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define os valores dos parâmetros com os dados do objeto 'cliente'
            ps.setString(1, p.getCodBarras());
            ps.setString(2, p.getDescricao());
            ps.setString(3, p.getCategoria());
            ps.setString(4, p.getUndMedida());
            ps.setDouble(5, p.getCusto());
            ps.setDouble(6, p.getPrecoVenda());
            ps.setString(7, p.getStatus());
            ps.setInt(8, p.getSaldo());
            ps.setBoolean(9, p.isControlaSaldo()); 
            ps.setInt(10, id);

            // Executa a inserção e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            return false;
        }

    }
        
    public static List<Produto> listar() {
        List<Produto> lista = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            //Armazenamos a query SQL em uma string
            //String sql = "SELECT id, nome, cpf, endereco, telefone, especialidade, convenioId FROM Medico";
            String sql = "SELECT * FROM produto";

            //Preparamos o comando para ser executado no banco
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

           while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("cod_barras"));
                p.setDescricao(rs.getString("descricao"));
                p.setCategoria(rs.getString("categoria"));
                p.setUndMedida(rs.getString("und_medida"));
                p.setCusto(rs.getDouble("custo"));
                p.setPrecoVenda(rs.getDouble("preco_venda"));
                p.setStatus(rs.getString("status"));
                p.setSaldo(rs.getInt("saldo"));
                p.setControlaSaldo(rs.getBoolean("controla_saldo"));
             
                lista.add(p);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar produtos: " + se.getMessage());
        }

        return lista;
    }

    
}
