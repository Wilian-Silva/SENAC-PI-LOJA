
package Classes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ItensVenda {
  
    private int id;
    private String nome;
    private String categoria;
    private double preco;
    private int quantidade;
    private double totalProduto;

    public ItensVenda(int id, String nome, String categoria, double preco, int quantidade, double totalProduto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidade = quantidade;
        this.totalProduto = totalProduto;
    }

    public ItensVenda() {
        
    }
        
    //METODOS
     public static ArrayList<ItensVenda> lista = new ArrayList<ItensVenda>(); //Criando um ArrayList da classe consulta com nome de lista
     
     public static ArrayList<ItensVenda> listar() { //metodo para carregar dados da lista
        return lista;
    }
     
    public static boolean excluir(int id) { //metodo para excluir item da lista
        try {
            if (id < 0) {
                JOptionPane.showMessageDialog(null, "Selecione um item para excluir na tabela!");
            } else {
                lista.remove(id);
                 JOptionPane.showMessageDialog(null, "Item exlcuído com sucesso!");
                return true;             
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao excluir da lista");
        }
        return false;
    }
    
     public static boolean adicionarItens(ItensVenda p) { //metodo para adicionar itens na lista

        //Exceções e Tratamento
        try {
            lista.add(p);
           
            return true;

        } catch (Exception e) {
            //Se tudo der errado
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar produto");

        }
       
        return false;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotalProduto() {
        return totalProduto;
    }

    public void setTotalProduto(double totalProduto) {
        this.totalProduto = totalProduto;
    }
    
    
    
    
}
