
package Classes;
public class Cliente {
    
    private int id;
    private String nome;
    private String cpf;
    private String RG;
    private String endereco;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;
    private String email;
    private String telefone;
     
     
    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String RG, String endereco, String bairro, String cidade, String cep, String estado, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.RG = RG;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.email = email;
        this.telefone = telefone;
    }

    
    
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
 

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

      @Override
    public String toString() {
        return  "Nome: " + nome  + " | CPF: " + cpf;
    }
    
 
}
