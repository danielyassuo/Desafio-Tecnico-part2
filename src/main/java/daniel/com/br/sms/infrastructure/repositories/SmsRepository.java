package daniel.com.br.sms.infrastructure.repositories;


import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SmsRepository extends JpaRepository<SmsMensagemEntity, Long> {

    List<SmsMensagemEntity> findByStatusEnvioAndDataEnvioAfter(StatusEnvioEnum statusEnvioEnum, LocalDateTime dataLimite);
}
