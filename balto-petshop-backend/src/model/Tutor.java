package model;

public class Tutor {
    private String nome;
    private int idade;
    private String telefone;
    private String gmail;
    private int id;

    public Tutor( String nome, int idade, String telefone, String gmail){
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.gmail = gmail;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getGmail(){
        return gmail;
    }

    public void setGmail(String gmail){
        this.gmail = gmail;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
