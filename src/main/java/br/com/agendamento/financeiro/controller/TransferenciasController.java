package br.com.agendamento.financeiro.controller;

import br.com.agendamento.financeiro.domain.TransferenciaDomain;
import br.com.agendamento.financeiro.dto.TransferenciaDTO;
import br.com.agendamento.financeiro.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
@Tag(name = "Transferências", description = "Gerenciamento de transferências financeiras")
@Slf4j
public class TransferenciasController {

    private final TransferenciaService transferenciaService;

    public TransferenciasController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova transferência", description = "Cria uma nova transferência no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<TransferenciaDomain> criar(@RequestBody TransferenciaDTO dto) {
        log.info("Iniciando criação de novas transferencias");
        TransferenciaDomain criada = transferenciaService.criar(dto);
        return ResponseEntity.ok(criada);
    }

    @GetMapping
    @Operation(summary = "Listar todas as transferências", description = "Retorna uma lista com todas as transferências agendadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transferências retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma transferência encontrada")
    })
    public ResponseEntity<List<TransferenciaDomain>> listar() {
        return ResponseEntity.ok(transferenciaService.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma transferência", description = "Atualiza uma transferência existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transferência não encontrada")
    })
    public ResponseEntity<TransferenciaDomain> atualizar(@PathVariable Long id, @RequestBody TransferenciaDTO dto) {
        TransferenciaDomain atualizada = transferenciaService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma transferência", description = "Deleta uma transferência existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transferência deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Transferência não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transferenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-conta-origem/{contaOrigem}")
    @Operation(summary = "Listar transferências por conta de origem",
            description = "Retorna uma lista de transferências filtradas pela conta de origem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transferências retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma transferência encontrada para esta conta de origem")
    })
    public ResponseEntity<List<TransferenciaDomain>> listarPorContaOrigem(@PathVariable String contaOrigem) {
        List<TransferenciaDomain> transferencias = transferenciaService.listarPorContaOrigem(contaOrigem);
        if (transferencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transferencias);
    }
}
