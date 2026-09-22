package daniel.com.br.sms.api.dto.request;

import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class SmsRequestDTO {

    private Long id;
    private String numeroTelefone;
    private StatusEnvioEnum statusEnvio;
    private LocalDateTime dataEnvio;
}
