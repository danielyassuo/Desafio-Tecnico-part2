package daniel.com.br.sms.api.requests;

import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;

import java.time.LocalDateTime;

public class SmsRequestDTOFixture {

    public static SmsRequestDTO build (Long id, String numeroTelefone, StatusEnvioEnum statusEnvio, LocalDateTime dataEnvio ) {
        return new SmsRequestDTO(id, numeroTelefone, statusEnvio, dataEnvio);
    }
}
