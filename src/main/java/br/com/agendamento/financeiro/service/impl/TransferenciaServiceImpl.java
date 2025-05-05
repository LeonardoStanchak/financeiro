package br.com.agendamento.financeiro.service.impl;

import br.com.agendamento.financeiro.domain.TransferenciaDomain;
import br.com.agendamento.financeiro.dto.TransferenciaDTO;
import br.com.agendamento.financeiro.entity.TransferenciaEntity;
import br.com.agendamento.financeiro.exception.TransferenciaException;
import br.com.agendamento.financeiro.mapper.TransferenciaMapper;
import br.com.agendamento.financeiro.repository.TransferenciaRepository;
import br.com.agendamento.financeiro.service.TransferenciaService;
import br.com.agendamento.financeiro.utils.TransferenciaUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository repository;

    public TransferenciaServiceImpl(TransferenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransferenciaDomain criar(TransferenciaDTO dto) {
        // Validações
        TransferenciaUtil.validarValorTransferencia(dto.getValor());
        TransferenciaUtil.validarDataTransferencia(dto.getDataTransferencia());

        // Calcular taxa usando a lógica definida
        BigDecimal taxa = TransferenciaUtil.calcularTaxa(dto);
        if (taxa == null) {
            throw new TransferenciaException("Não há taxa aplicável para a data informada.");
        }

        // Ajustar a data de agendamento
        dto.setDataAgendamento(LocalDate.now());

        // Converter DTO para Entity
        TransferenciaEntity entity = TransferenciaMapper.toEntity(dto, taxa);

        // Salvar e retornar a transferência
        TransferenciaEntity salvo = repository.save(entity);
        return TransferenciaMapper.toDomain(salvo);
    }

    @Override
    public List<TransferenciaDomain> listar() {
        return repository.findAll().stream()
                .map(TransferenciaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TransferenciaDomain atualizar(Long id, TransferenciaDTO dto) {
        // Validações
        TransferenciaUtil.validarValorTransferencia(dto.getValor());
        TransferenciaUtil.validarDataTransferencia(dto.getDataTransferencia());

        // Buscar entidade existente
        TransferenciaEntity atual = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transferência não encontrada"));

        // Calcular taxa
        BigDecimal taxa = TransferenciaUtil.calcularTaxa(dto);
        if (taxa == null) {
            throw new TransferenciaException("Não há taxa aplicável para a data informada.");
        }

        // Atualizar os dados
        atual.setContaOrigem(dto.getContaOrigem());
        atual.setContaDestino(dto.getContaDestino());
        atual.setValor(dto.getValor());
        atual.setDataTransferencia(dto.getDataTransferencia());
        atual.setDataAgendamento(LocalDate.now());
        atual.setTaxa(taxa);

        // Salvar e retornar a transferência atualizada
        return TransferenciaMapper.toDomain(repository.save(atual));
    }

    @Override
    public List<TransferenciaDomain> listarPorContaOrigem(String contaOrigem) {
        return repository.findByContaOrigem(contaOrigem).stream()
                .map(TransferenciaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Transferência não encontrada");
        }
        repository.deleteById(id);
    }
}
