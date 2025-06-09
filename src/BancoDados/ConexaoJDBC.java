
package BancoDados;
import java.sql.*;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Implementa AutoCloseable para que possa ser usada em blocos try-with-resources.
 */
public class ConexaoJDBC implements AutoCloseable {

    // Objeto de conexão com o banco
    private Connection conexao;

    /**
     * Retorna a conexão atual com o banco de dados.
     * @return 
     */
    public Connection getConexao() {
        return conexao;
    }

    /**
     * Realiza a conexão com o banco de dados MySQL.
     * Lança SQLException para que o erro possa ser tratado por quem chamou.
     * @throws java.sql.SQLException
     */
    public void conectar() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco: endereço, usuário e senha
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/loja", "root", "");

        } catch (ClassNotFoundException cn) {
            // Caso o driver JDBC não seja encontrado

        } catch (SQLException sql) {
            // Problemas ao conectar (usuário, senha, nome do banco, etc)
            throw sql; // repassa o erro para quem chamou a conexão
        }
    }

    /**
     * Fecha a conexão com o banco de dados, se estiver aberta.
     * @throws java.sql.SQLException
     */
    public void desconectar() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    /**
     * Implementação do método da interface AutoCloseable.
     * Permite que a conexão seja fechada automaticamente ao sair de um bloco try-with-resources.
     * @throws java.sql.SQLException
     */
    @Override
    public void close() throws SQLException {
        desconectar();
    }
}
