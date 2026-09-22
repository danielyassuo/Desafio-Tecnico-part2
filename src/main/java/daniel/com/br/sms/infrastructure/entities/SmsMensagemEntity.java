package daniel.com.br.sms.infrastructure.entities;


import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import jakarta.persistence.*;
import jakarta.validation.valueextraction.UnwrapByDefault;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class SmsMensagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "numero_telefone", length = 11)
    private String numeroTelefone;

    @Column(name = "status_do_envio")
    private StatusEnvioEnum statusEnvio;

    @Column(name = "data_do_envio")
    private LocalDateTime dataEnvio;
}
