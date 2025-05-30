package repository;
import model.Serviço;
import java.util.ArrayList;
import java.util.List;

public class ServiçoRepository {
    private List<Serviço> servicos;

    public ServiçoRepository() {
        this.servicos = new ArrayList<>();
    }

    public void adicionarServico(Serviço servico) {
        servicos.add(servico);
    }

    public List<Serviço> listarServicos() {
        return servicos;
    }

    public Serviço buscarPorId(int id) {
        for (Serviço servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }

    public boolean removerServico(int id) {
        Serviço servico = buscarPorId(id);
        if (servico != null) {
            servicos.remove(servico);
            return true;
        }
        return false;
    }

    public boolean atualizarStatus(int id, String novoStatus) {
        Serviço servico = buscarPorId(id);
        if (servico != null) {
            servico.setStatus(novoStatus);
            return true;
        }
        return false;
    }

    public int gerarNovoId() {
        int maiorId = 0;
        for (Serviço s : servicos) {
            if (s.getId() > maiorId) {
                maiorId = s.getId();
            }
        }
        return maiorId + 1;
    }


}