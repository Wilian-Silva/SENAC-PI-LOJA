
package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QtdProdutosDialog extends JDialog {

    private JTextField txtQuantidade;
    private JButton btnConfirmar;
    private int quantidade = -1;  // valor padrão, caso o usuário cancele

    public QtdProdutosDialog(JFrame parent) {
        super(parent, "Informar Quantidade", true);
        setSize(300, 150);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        // Painel de entrada
        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Quantidade:"));

        txtQuantidade = new JTextField(10);
        painelEntrada.add(txtQuantidade);
        add(painelEntrada, BorderLayout.CENTER);

        // Painel de botões
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> confirmar());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> cancelar());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Enter = confirmar
        getRootPane().setDefaultButton(btnConfirmar);
    }

    private void confirmar() {
        try {
            quantidade = Integer.parseInt(txtQuantidade.getText());
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número inteiro válido.");
        }
    }

    private void cancelar() {
        quantidade = -1;  // ou 0, dependendo da sua regra
        dispose();
    }

    // Método público para exibir o diálogo e retornar a quantidade
    public int mostrarDialogo() {
        setVisible(true);
        return quantidade;
    }
}