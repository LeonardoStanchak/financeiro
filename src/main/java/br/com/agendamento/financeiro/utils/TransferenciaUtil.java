package br.com.agendamento.financeiro.utils;

import br.com.agendamento.financeiro.dto.TransferenciaDTO;
import br.com.agendamento.financeiro.exception.TransferenciaException;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class TransferenciaUtil {

    // Cálculo da taxa de transferência com base na tabela
    public static BigDecimal calcularTaxa(TransferenciaDTO dto) {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), dto.getDataTransferencia());
        BigDecimal valor = dto.getValor();

        if (dias == 0) {
            return new BigDecimal("3.00").add(valor.multiply(new BigDecimal("0.025")));
        } else if (dias >= 1 && dias <= 10) {
            return new BigDecimal("12.00");
        } else if (dias >= 11 && dias <= 20) {
            return valor.multiply(new BigDecimal("0.082"));
        } else if (dias >= 21 && dias <= 30) {
            return valor.multiply(new BigDecimal("0.069"));
        } else if (dias >= 31 && dias <= 40) {
            return valor.multiply(new BigDecimal("0.047"));
        } else if (dias >= 41 && dias <= 50) {
            return valor.multiply(new BigDecimal("0.017"));
        } else {
            return null; // Fora das regras
        }
    }

    // Verifica se a data de transferência é no passado
    public static void validarDataTransferencia(LocalDate dataTransferencia) {
        if (dataTransferencia.isBefore(LocalDate.now())) {
            throw new TransferenciaException("A data de transferência não pode ser no passado.");
        }
    }

    // Valida se o valor da transferência é positivo
    public static void validarValorTransferencia(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransferenciaException("O valor da transferência deve ser positivo.");
        }
    }

}
