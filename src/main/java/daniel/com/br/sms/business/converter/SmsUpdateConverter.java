package daniel.com.br.sms.business.converter;


import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SmsUpdateConverter {

    SmsMensagemEntity updateSms (SmsRequestDTO dto, @MappingTarget SmsMensagemEntity entity);
}
