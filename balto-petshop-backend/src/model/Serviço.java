package model;
import java.time.LocalDateTime;

public class Serviço {
    private int id;
    private String nome_do_servico;
    private String descricao;
    private double preco;
    private double duracao_servico;
    private String status;

    private Pet pet;
    private Tutor tutor;
    private LocalDateTime dataHoraAgendada;

    public Serviço(int id, String nome_do_servico, String descricao, double preco,
                   double duracao_servico, Pet pet, Tutor tutor, LocalDateTime dataHoraAgendada) {
        this.id = id;
        this.nome_do_servico = nome_do_servico;
        this.descricao = descricao;
        this.preco = preco;
        this.duracao_servico = duracao_servico;
        this.pet = pet;
        this.tutor = tutor;
        this.dataHoraAgendada = dataHoraAgendada;
        this.status = "Pendente";
    }

    public int getId() {
        return id;
    }

    public String getNome_do_servico() {
        return nome_do_servico;
    }

    public void setNome_do_servico(String nome_do_servico) {
        this.nome_do_servico = nome_do_servico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getDuracao_servico() {
        return duracao_servico;
    }

    public void setDuracao_servico(double duracao_servico) {
        this.duracao_servico = duracao_servico;
    }

    public String getStatus() {
        return status;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor){
        this.tutor = tutor;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
