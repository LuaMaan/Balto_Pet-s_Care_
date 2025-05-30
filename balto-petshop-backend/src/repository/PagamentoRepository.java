package repository;

import model.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository {
    private List<Pagamento> listaPagamentos = new ArrayList<>();

    public void adicionarPagamento(Pagamento pagamento) {
        listaPagamentos.add(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return listaPagamentos;
    }
}

