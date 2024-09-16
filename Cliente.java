package ccfc;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String servicosRealizados;

    public Cliente(String nome, String telefone, String servicosRealizados) {
        this.nome = nome;
        this.telefone = telefone;
        this.servicosRealizados = servicosRealizados;
    }

    public Cliente(int id, String nome, String telefone, String servicosRealizados) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.servicosRealizados = servicosRealizados;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getServicosRealizados() {
        return servicosRealizados;
    }

    public void setServicosRealizados(String servicosRealizados) {
        this.servicosRealizados = servicosRealizados;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", servicosRealizados='" + servicosRealizados + '\'' +
                '}';
    }
}