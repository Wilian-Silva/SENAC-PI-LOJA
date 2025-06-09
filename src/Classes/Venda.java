
package Classes;
import java.util.ArrayList;
import java.util.List;



public class Venda {
  
    private int id;
    private String data;
    private int id_cliente;
    private String produto;
    private ArrayList<VendaItem> itens;
    private double total;
    private double quantidade;
    private double preco_unit;

    public Venda() {
    }

    public Venda(int id, String data, int id_cliente, String produto, ArrayList<ItensVenda> itens, double total, double quantidade, double preco_unit) {
        this.id = id;
        this.data = data;
        this.id_cliente = id_cliente;
        this.produto = produto;      
        this.total = total;
        this.quantidade = quantidade;
        this.preco_unit = preco_unit;
    }

    
public List<VendaItem> getItens() {
    return itens;
}

public void setItens(ArrayList<VendaItem> itens) {
    this.itens = itens;
}

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_unit() {
        return preco_unit;
    }

    public void setPreco_unit(double preco_unit) {
        this.preco_unit = preco_unit;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }   
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }



    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
        
    
    
    
    
}

