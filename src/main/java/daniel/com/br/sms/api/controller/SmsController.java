package daniel.com.br.sms.api.controller;


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


    @PutMapping("/status")
    @Operation(summary = "Altera o status da mensagem", description = "Realiza a alteração do status de mensagens sms")
    @ApiResponse(responseCode = "200", description = "mensagem alterada com sucesso")
    @ApiResponse(responseCode = "404", description = "Id de mensagem não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro no Servidor")
    public ResponseEntity<SmsResponseDTO> alterarStatusMensagem (@RequestBody Long id, @RequestBody StatusEnvioEnum statusEnvioEnum){
        return ResponseEntity.ok(service.alteraStatusSms(id, statusEnvioEnum));
    }

    @GetMapping("/relatorio")
    @Operation(summary = "Gera relatório das mensagens sms", description = "Através do status da mensagem ele gera uma busca de mensagens")
    @ApiResponse(responseCode = "200", description = "Mensagens encontradas e relatório gerado com sucesso")
    @ApiResponse(responseCode = "401", description = "status inválido")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<SmsResponseDTO>> gerarRelatorioSms(@RequestBody StatusEnvioEnum statusEnvioEnum){
        return ResponseEntity.ok(service.gerarRelatorio(statusEnvioEnum));
    }


}
