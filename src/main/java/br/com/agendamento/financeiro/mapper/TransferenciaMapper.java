package br.com.agendamento.financeiro.mapper;

import br.com.agendamento.financeiro.domain.TransferenciaDomain;
import br.com.agendamento.financeiro.dto.TransferenciaDTO;
import br.com.agendamento.financeiro.entity.TransferenciaEntity;

import java.math.BigDecimal;

public class TransferenciaMapper {

    // Converte TransferenciaDTO para TransferenciaEntity
    public static TransferenciaEntity toEntity(TransferenciaDTO dto, BigDecimal taxa) {
        TransferenciaEntity entity = new TransferenciaEntity();
        entity.setContaOrigem(dto.getContaOrigem());
        entity.setContaDestino(dto.getContaDestino());
        entity.setValor(dto.getValor());
        entity.setTaxa(taxa);
        entity.setDataTransferencia(dto.getDataTransferencia());
        entity.setDataAgendamento(dto.getDataAgendamento());
        return entity;
    }

    // Converte TransferenciaEntity para TransferenciaDomain
    public static TransferenciaDomain toDomain(TransferenciaEntity entity) {
        TransferenciaDomain domain = new TransferenciaDomain();
        domain.setId(entity.getId());
        domain.setContaOrigem(entity.getContaOrigem());
        domain.setContaDestino(entity.getContaDestino());
        domain.setValor(entity.getValor());
        domain.setTaxa(entity.getTaxa());
        domain.setDataTransferencia(entity.getDataTransferencia());
        domain.setDataAgendamento(entity.getDataAgendamento());
        return domain;
    }
}
