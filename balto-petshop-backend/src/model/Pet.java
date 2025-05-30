package model;

public class Pet {
    private String nomePet;
    private String racaPet;
    private int idadePet;
    private Tutor tutor;
    private String observacao;
    private int id;

    public Pet() {
    }

    public Pet(String nome, String raca, int idade, Tutor tutor, String observacao) {
        this.nomePet = nome;
        this.racaPet = raca;
        this.idadePet = idade;
        this.tutor = tutor;
        this.observacao = observacao;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getRacaPet() {
        return racaPet;
    }

    public void setRacaPet(String racaPet) {
        this.racaPet = racaPet;
    }

    public int getIdadePet() {
        return idadePet;
    }

    public void setIdadePet(int idadePet) {
        this.idadePet = idadePet;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
