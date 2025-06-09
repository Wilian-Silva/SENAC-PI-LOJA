
package UserInterfaces;

import BancoDados.ConexaoJDBC;
import Classes.Cliente;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ListClientesDialog extends JDialog {

    private JTextField txtBusca;
    private JList<Cliente> listaClientes;
    private DefaultListModel<Cliente> modeloLista;
    private Cliente clienteSelecionado = null;

    public ListClientesDialog(JFrame parent) {
    super(parent, "Selecionar Cliente", true);
    setSize(500, 400);
    setLayout(new BorderLayout());

    // Painel superior com campo de busca
    JPanel painelBusca = new JPanel(new BorderLayout());
    txtBusca = new JTextField();
    JButton btnBuscar = new JButton("Buscar");

    painelBusca.add(new JLabel("Nome:"), BorderLayout.WEST);
    painelBusca.add(txtBusca, BorderLayout.CENTER);
    painelBusca.add(btnBuscar, BorderLayout.EAST);

    add(painelBusca, BorderLayout.NORTH);

    // Lista de clientes
    modeloLista = new DefaultListModel<>();
    listaClientes = new JList<>(modeloLista);
    add(new JScrollPane(listaClientes), BorderLayout.CENTER);

    // Botão Selecionar
    JButton btnSelecionar = new JButton("Selecionar");
    btnSelecionar.addActionListener(e -> {
        clienteSelecionado = listaClientes.getSelectedValue();
        dispose();
    });
    add(btnSelecionar, BorderLayout.SOUTH);

    // Evento do botão "Buscar"
    btnBuscar.addActionListener(e -> buscarClientes(txtBusca.getText()));

    // ➕ Evento de pesquisa dinâmica
    txtBusca.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            buscarClientes(txtBusca.getText());
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            buscarClientes(txtBusca.getText());
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            buscarClientes(txtBusca.getText());
        }
    });

    // Buscar todos inicialmente (opcional)
    buscarClientes("");
}

public Cliente mostrarDialogo() {
    setLocationRelativeTo(getParent());
    setVisible(true);
    return clienteSelecionado;
}
    private void buscarClientes(String nomeParcial) {
        modeloLista.clear(); // limpa a lista antes de carregar os resultados
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? ORDER BY nome";

        try (ConexaoJDBC conexao = new ConexaoJDBC()) {
            conexao.conectar();

            try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
                stmt.setString(1, "%" + nomeParcial + "%");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("endereco"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email")
                    );
                    modeloLista.addElement(c);
                }

                if (modeloLista.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nenhum cliente encontrado.");
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar clientes: " + e.getMessage());
        }
    }
}
