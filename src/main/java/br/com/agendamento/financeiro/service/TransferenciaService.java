package br.com.agendamento.financeiro.service;

import br.com.agendamento.financeiro.domain.TransferenciaDomain;
import br.com.agendamento.financeiro.dto.TransferenciaDTO;

import java.util.List;

public interface TransferenciaService {
    TransferenciaDomain criar(TransferenciaDTO dto);
    List<TransferenciaDomain> listar();
    TransferenciaDomain atualizar(Long id, TransferenciaDTO dto);
    void deletar(Long id);
    List<TransferenciaDomain> listarPorContaOrigem(String contaOrigem); // Novo método
}
