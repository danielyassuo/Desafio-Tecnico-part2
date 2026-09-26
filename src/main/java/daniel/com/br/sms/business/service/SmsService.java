package daniel.com.br.sms.business.service;


import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.business.converter.Converter;
import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import daniel.com.br.sms.infrastructure.exceptions.ResourcesNotFoundExceptions;
import daniel.com.br.sms.infrastructure.repositories.SmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final SmsRepository repository;
    private final Converter converter;



    public SmsResponseDTO alteraStatusSms (Long id, StatusEnvioEnum status) {
        SmsMensagemEntity entity = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundExceptions("Id não encontrado "+ id));

        entity.setStatusEnvio(status);
        repository.save(entity);

        return converter.paraDTO(entity);
    }

    public List<SmsResponseDTO> gerarRelatorio (StatusEnvioEnum statusEnvioEnum) {
        LocalDateTime dataHoraLimite = LocalDateTime.now().minusHours(24);

        List<SmsMensagemEntity> entity = repository.findByStatusEnvioAndDataEnvioAfter(statusEnvioEnum, dataHoraLimite);

        return converter.paraListaResponse(entity);
    }


}
