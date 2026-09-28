package daniel.com.br.sms.api.responses;

import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;

import java.time.LocalDateTime;

public class SmsResponseDTOFixture {
    public static SmsResponseDTO build (Long id, String numeroTelefone, StatusEnvioEnum statusEnvio, LocalDateTime dataEnvio ) {
        return new SmsResponseDTO(id, numeroTelefone, statusEnvio, dataEnvio);
    }
}
