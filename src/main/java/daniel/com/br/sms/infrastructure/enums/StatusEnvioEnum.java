package daniel.com.br.sms.infrastructure.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnvioEnum {
    ENVIADO,
    RECEBIDO,
    ERRO_DE_ENVIO;
}
