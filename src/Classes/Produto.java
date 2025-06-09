
package Classes;

public class Produto {
    
    private int id;
    private String codBarras;
    private String descricao;
    private String categoria;
    private String undMedida;
    private double custo;
    private double precoVenda;
    private String status;
    private int saldo;
    private boolean controlaSaldo;
    //private int quantidade;
    

    public Produto() {
    }

    public Produto(int id, String codBarras, String descricao, String categoria, String undMedida, double custo, double precoVenda, String status, int saldo, boolean controlaSaldo) {
        this.id = id;
        this.codBarras = codBarras;
        this.descricao = descricao;
        this.categoria = categoria;
        this.undMedida = undMedida;
        this.custo = custo;
        this.precoVenda = precoVenda;
        this.status = status;
        this.saldo = saldo;
        this.controlaSaldo = controlaSaldo;
    }

   

    
    
    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(String undMedida) {
        this.undMedida = undMedida;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public boolean isControlaSaldo() {
        return controlaSaldo;
    }

    public void setControlaSaldo(boolean controlaSaldo) {
        this.controlaSaldo = controlaSaldo;
    }

    @Override
    public String toString() {
        return "Produto:" + descricao + " | R$: " + precoVenda;
    }
    
}
    
 
    