
package DAO;

import BancoDados.ConexaoJDBC;
import Classes.Produto;
import Classes.Venda;
import Classes.VendaItem;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class VendaDAO {
  
   
  public static boolean salvarVenda(Venda v) {
    String sqlVenda = "INSERT INTO vendas (id_cliente, data_venda, total_venda, status) VALUES (?, NOW(), ?, ?)";
    String sqlItem = "INSERT INTO venda_itens (venda_id, produto_id, quantidade, preco_unitario, total_item) VALUES (?, ?, ?, ?, ?)";

    try (ConexaoJDBC conn = new ConexaoJDBC()) {
        conn.conectar();
        Connection connection = conn.getConexao();

        connection.setAutoCommit(false);

        try (PreparedStatement psVenda = connection.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS)) {
            psVenda.setInt(1, v.getId_cliente());
            psVenda.setDouble(2, v.getTotal());
            psVenda.setString(3, "Finalizada"); // Corrigido índice

            psVenda.executeUpdate();

            int vendaId;
            try (ResultSet rs = psVenda.getGeneratedKeys()) {
                if (rs.next()) {
                    vendaId = rs.getInt(1);
                } else {
                    connection.rollback();
                    return false;
                }
            }

            try (PreparedStatement psItem = connection.prepareStatement(sqlItem)) {
                for (VendaItem item : v.getItens()) {
                    psItem.setInt(1, vendaId);
                    psItem.setInt(2, item.getProdutoId());
                    psItem.setInt(3, item.getQuantidade());
                    psItem.setDouble(4, item.getPrecoUnitario());
                    psItem.setDouble(5, item.getTotalItem());
                    psItem.addBatch();
                }
                psItem.executeBatch();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    } catch (SQLException e) {
        e.printStackTrace();
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
