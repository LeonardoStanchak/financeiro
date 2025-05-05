package br.com.agendamento.financeiro.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Informações detalhadas sobre a transferência financeira")
public class TransferenciaDomain {

    @Schema(description = "ID da transferência", example = "1", required = true)
    private Long id;

    @Schema(description = "Conta de origem da transferência", example = "1234567890", required = true)
    private String contaOrigem;

    @Schema(description = "Conta de destino da transferência", example = "0987654321", required = true)
    private String contaDestino;

    @Schema(description = "Valor da transferência", example = "1500.00", required = true)
    private BigDecimal valor;

    @Schema(description = "Taxa aplicada sobre a transferência", example = "45.00", required = true)
    private BigDecimal taxa;

    @Schema(description = "Data de agendamento da transferência", example = "2023-05-10", required = true)
    private LocalDate dataAgendamento;

    @Schema(description = "Data da transferência", example = "2023-05-15", required = true)
    private LocalDate dataTransferencia;
}
