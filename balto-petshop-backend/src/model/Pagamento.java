package model;
import java.time.LocalDateTime;

public class Pagamento {

        private int id;
        private double valorPago;
        private LocalDateTime dataPagamento;
        private String formaPagamento;
        private String status_do_pagamento;

        private Serviço servico;

        public Pagamento(int id, double valorPago, LocalDateTime dataPagamento,
                         String formaPagamento, String status_do_pagamento, Serviço servico) {
            this.id = id;
            this.valorPago = valorPago;
            this.dataPagamento = dataPagamento;
            this.formaPagamento = formaPagamento;
            this.status_do_pagamento = status_do_pagamento;
            this.servico = servico;
        }

        public int getId() {
            return id;
        }

        public double getValorPago() {
            return valorPago;
        }

        public void setValorPago(double valorPago) {
            this.valorPago = valorPago;
        }

        public LocalDateTime getDataPagamento() {
            return dataPagamento;
        }

        public void setDataPagamento(LocalDateTime dataPagamento) {
            this.dataPagamento = dataPagamento;
        }

        public String getFormaPagamento() {
            return formaPagamento;
        }

        public void setFormaPagamento(String formaPagamento) {
            this.formaPagamento = formaPagamento;
        }

        public String getStatus() {
            return status_do_pagamento;
        }

        public void setStatus(String status_do_pagamento) {
            this.status_do_pagamento = status_do_pagamento;
        }

        public Serviço getServico() {
            return servico;
        }

        public void setServico(Serviço servico) {
            this.servico = servico;
        }

        public void marcarComoPago() {
            this.status_do_pagamento = "Pago";
        }

        public void cancelarPagamento() {
            this.status_do_pagamento = "Cancelado";
        }

        public String consultarDetalhesPagamento() {
            return "Pagamento ID: " + id +
                    "Valor: R$" + valorPago +
                    "Data: " + dataPagamento +
                    "Forma: " + formaPagamento +
                    "Status: " + status_do_pagamento +
                    "Serviço: " + (servico != null ? servico.getNome_do_servico() : "Nenhum");
        }
    }
