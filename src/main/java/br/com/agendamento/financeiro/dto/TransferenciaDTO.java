package br.com.agendamento.financeiro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados necessários para criar ou atualizar uma transferência financeira")
public class TransferenciaDTO {

    @Schema(description = "Conta de origem da transferência", example = "1234567890", required = true)
    private String contaOrigem;

    @Schema(description = "Conta de destino da transferência", example = "0987654321", required = true)
    private String contaDestino;

    @Schema(description = "Valor da transferência", example = "1500.00", required = true)
    private BigDecimal valor;

    @Schema(description = "Data da transferência", example = "2023-05-15", required = true)
    private LocalDate dataTransferencia;

    @Schema(description = "Data de agendamento da transferência", example = "2023-05-10", required = true)
    private LocalDate dataAgendamento;
}
