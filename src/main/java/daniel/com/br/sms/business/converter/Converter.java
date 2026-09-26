package daniel.com.br.sms.business.converter;


import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Converter {

    SmsResponseDTO paraDTO (SmsMensagemEntity entity);

    SmsMensagemEntity paraEntity (SmsRequestDTO dto);

    List<SmsResponseDTO> paraListaResponse (List<SmsMensagemEntity> entity);
}
