package repository;
import model.Tutor;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {
    private List<Tutor> tutores = new ArrayList<>();
    private int proximoId = 1;

    public Tutor buscarTutorPorNomeOuId(String dado) {
        for (Tutor t : tutores) {
            if (t.getNome().equalsIgnoreCase(dado)) return t;
            if (String.valueOf(t.getId()).equals(dado)) return t;
        }
        return null;
    }

    public void adicionarTutor(Tutor tutor) {
        tutor.setId(proximoId);
        tutores.add(tutor);
        proximoId++;
    }

    public Tutor buscarPorId(int id) {
        for (Tutor t : tutores) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public boolean atualizarTutor(Tutor tutorAtualizado) {
        for (int i = 0; i < tutores.size(); i++) {
            if (tutores.get(i).getId() == tutorAtualizado.getId()) {
                tutores.set(i, tutorAtualizado);
                return true;
            }
        }
        return false;
    }
    public boolean removerTutor(int id) {
        return tutores.removeIf(t -> t.getId() == id);
    }

    public List<Tutor> getTodosTutores() {
        return tutores;
    }

    public List<Tutor> listarTutores() {
        return tutores;
    }
    public TutorRepository() {
        this.tutores = new ArrayList<>();
    }
}