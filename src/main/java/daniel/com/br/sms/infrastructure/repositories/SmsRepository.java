package daniel.com.br.sms.infrastructure.repositories;


import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsMensagemEntity, Long> {
}
