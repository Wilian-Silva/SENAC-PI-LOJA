
package UserInterfaces;
import BancoDados.ConexaoJDBC;
import Classes.Produto;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ListProdutosDialog extends JDialog {

    private JTextField txtBusca;
    private JList<Produto> listaProdutos;
    private DefaultListModel<Produto> modeloLista;
    private Produto produtoSelecionado = null;

    public ListProdutosDialog(JFrame parent) {
    super(parent, "Selecionar Produto", true);
    setSize(500, 400);
    setLayout(new BorderLayout());

    // Painel superior com campo de busca
    JPanel painelBusca = new JPanel(new BorderLayout());
    txtBusca = new JTextField();
    JButton btnBuscar = new JButton("Buscar");

    painelBusca.add(new JLabel("Produto:"), BorderLayout.WEST);
    painelBusca.add(txtBusca, BorderLayout.CENTER);
    painelBusca.add(btnBuscar, BorderLayout.EAST);

    add(painelBusca, BorderLayout.NORTH);

    // Lista de podutos
    modeloLista = new DefaultListModel<>();
    listaProdutos = new JList<>(modeloLista);
    add(new JScrollPane(listaProdutos), BorderLayout.CENTER);

    // Botão Selecionar
    JButton btnSelecionar = new JButton("Selecionar");
    btnSelecionar.addActionListener(e -> {
        produtoSelecionado = listaProdutos.getSelectedValue();
        dispose();
    });
    add(btnSelecionar, BorderLayout.SOUTH);

    // Evento do botão "Buscar"
    btnBuscar.addActionListener(e -> buscarProdutos(txtBusca.getText()));

    // ➕ Evento de pesquisa dinâmica
    txtBusca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            buscarProdutos(txtBusca.getText());
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            buscarProdutos(txtBusca.getText());
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            buscarProdutos(txtBusca.getText());
        }
    });

    // Buscar todos inicialmente (opcional)
    buscarProdutos("");
}

public Produto mostrarDialogo() {
    setLocationRelativeTo(getParent());
    setVisible(true);
    return produtoSelecionado;
}
    private void buscarProdutos(String nomeParcial) {
        modeloLista.clear(); // limpa a lista antes de carregar os resultados
        String sql = "SELECT * FROM produto WHERE descricao LIKE ? ORDER BY descricao ";

        try (ConexaoJDBC conexao = new ConexaoJDBC()) {
            conexao.conectar();

            try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
                stmt.setString(1, "%" + nomeParcial + "%");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("cod_barras"),
                        rs.getString("descricao"),
                        rs.getString("categoria"),
                        rs.getString("und_medida"),
                        rs.getDouble("custo"),
                        rs.getDouble("preco_venda"),
                        rs.getString("status"),
                        rs.getInt("saldo"),
                        rs.getBoolean("controla_saldo")
                       
                    );
                    modeloLista.addElement(p);
                }

                if (modeloLista.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar produtos: " + e.getMessage());
        }
    }
}
