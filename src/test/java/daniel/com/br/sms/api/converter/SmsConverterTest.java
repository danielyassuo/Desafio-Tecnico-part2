package daniel.com.br.sms.api.converter;

import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.business.converter.Converter;
import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SmsConverterTest {


    Converter converter;


    SmsMensagemEntity smsMensagemEntity;


    SmsRequestDTO smsRequestDTO;


    SmsResponseDTO smsResponseDTO;

    List<SmsMensagemEntity> listaEntidade;

    List<SmsResponseDTO> listaResponse;

    @BeforeEach
    public void setup () {
        converter = Mappers.getMapper(Converter.class);

        smsMensagemEntity = SmsMensagemEntity.builder()
                .id(1L)
                .numeroTelefone("43991368486")
                .statusEnvio(StatusEnvioEnum.ENVIADO)
                .dataEnvio(LocalDateTime.of(2026, 9, 25, 15, 30))
                .build();

        smsRequestDTO = SmsRequestDTO.builder()
                .id(1L)
                .numeroTelefone("43991368486")
                .statusEnvio(StatusEnvioEnum.ENVIADO)
                .dataEnvio(LocalDateTime.of(2026, 9, 25, 15, 30))
                .build();

        smsResponseDTO = SmsResponseDTO.builder()
                .id(1L)
                .numeroTelefone("43991368486")
                .statusEnvio(StatusEnvioEnum.ENVIADO)
                .dataEnvio(LocalDateTime.of(2026, 9, 25, 15, 30))
                .build();

         listaEntidade = List.of(smsMensagemEntity);
         listaResponse = List.of(smsResponseDTO);
    }

    @Test
    void deveConverterParaDTO () {
        SmsResponseDTO dto = converter.paraDTO(smsMensagemEntity);

        assertEquals(smsResponseDTO, dto);
    }

    @Test
    void deveConverterParaEntity () {
        SmsMensagemEntity entity = converter.paraEntity(smsRequestDTO);

        assertEquals(smsMensagemEntity, entity);
    }

    @Test
    void deveConverterParaList () {
        List<SmsResponseDTO> listResponse = converter.paraListaResponse(listaEntidade);

        assertEquals(listaResponse, listResponse);
    }
}
