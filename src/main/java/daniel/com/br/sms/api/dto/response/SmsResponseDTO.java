package daniel.com.br.sms.api.dto.response;

import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class SmsResponseDTO {


    private Long id;
    private String numeroTelefone;
    private StatusEnvioEnum statusEnvio;
    private LocalDateTime dataEnvio;
}
