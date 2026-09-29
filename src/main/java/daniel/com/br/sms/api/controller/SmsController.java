package daniel.com.br.sms.api.controller;


import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.business.service.SmsService;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
@Tag(name = "Sms", description = "Altera o status e faz busca de mensagens sms")
public class SmsController {

    private final SmsService service;


    @PostMapping
    @Operation(summary = "Salva uma nova Mensagem Sms", description = "Realiza o cadastro de um novo SMS")
    @ApiResponse(responseCode = "200", description = "Sms cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Estrutura inválida, BAD REQUEST")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<SmsResponseDTO> salvaSms (@RequestBody SmsRequestDTO smsRequestDTO) {
        return ResponseEntity.ok(service.salvarMensagemSms(smsRequestDTO));
    }

    @DeleteMapping("/deletar")
    @Operation(summary = "Deleta Sms", description = "Faz a deleção de SMS pelo id")
    @ApiResponse(responseCode = "200", description = "SMS deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "ID de mensagem não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarMensagemSms (@RequestParam Long id) {
        service.deletaSms(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualiza os dados do SMS", description = "Atualiza os dados de um SMS já cadastrado no sistema")
    @ApiResponse(responseCode = "200", description = "SMS atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    @ApiResponse(responseCode = "404", description = "ID de mensagem não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<SmsResponseDTO> atualizarDadosSms (@RequestParam Long id, @RequestBody SmsRequestDTO smsRequestDTO){
        return ResponseEntity.ok(service.alterarDadosSms(id, smsRequestDTO));
    }


    @PutMapping("/status")
    @Operation(summary = "Altera o status da mensagem", description = "Realiza a alteração do status de mensagens sms")
    @ApiResponse(responseCode = "200", description = "mensagem alterada com sucesso")
    @ApiResponse(responseCode = "404", description = "Id de mensagem não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro no Servidor")
    public ResponseEntity<SmsResponseDTO> alterarStatusMensagem (@RequestParam Long id, @RequestParam StatusEnvioEnum statusEnvioEnum){
        return ResponseEntity.ok(service.alteraStatusSms(id, statusEnvioEnum));
    }

    @GetMapping("/relatorio")
    @Operation(summary = "Gera relatório das mensagens sms", description = "Através do status da mensagem ele gera uma busca de mensagens")
    @ApiResponse(responseCode = "200", description = "Mensagens encontradas e relatório gerado com sucesso")
    @ApiResponse(responseCode = "401", description = "status inválido")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<SmsResponseDTO>> gerarRelatorioSms(@RequestParam StatusEnvioEnum statusEnvioEnum){
        return ResponseEntity.ok(service.gerarRelatorio(statusEnvioEnum));
    }


}
