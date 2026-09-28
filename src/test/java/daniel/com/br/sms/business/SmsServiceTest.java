package daniel.com.br.sms.business;


import daniel.com.br.sms.api.dto.request.SmsRequestDTO;
import daniel.com.br.sms.api.dto.response.SmsResponseDTO;
import daniel.com.br.sms.business.converter.Converter;
import daniel.com.br.sms.business.service.SmsService;
import daniel.com.br.sms.infrastructure.entities.SmsMensagemEntity;
import daniel.com.br.sms.infrastructure.enums.StatusEnvioEnum;
import daniel.com.br.sms.infrastructure.exceptions.ResourcesNotFoundExceptions;
import daniel.com.br.sms.infrastructure.repositories.SmsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SmsServiceTest {

    @InjectMocks
    SmsService service;

    @Mock
    SmsRepository repository;

    @Mock
    Clock clock;


    @Mock
    ResourcesNotFoundExceptions resourcesNotFoundExceptions;

    @Mock
    Converter converter;

    Instant agora;

    LocalDateTime limiteEsperado;

    SmsMensagemEntity smsMensagemEntity;


    SmsRequestDTO smsRequestDTO;


    SmsResponseDTO smsResponseDTO;

    List<SmsMensagemEntity> listaEntidade;

    List<SmsResponseDTO> listaResponse;

    @BeforeEach
    public void setup () {

        agora = Instant.parse("2026-09-26T12:00:00Z");
        limiteEsperado = LocalDateTime.of(2026, 9, 25, 12, 0);


        smsMensagemEntity = SmsMensagemEntity.builder()
                .id(1L)
                .numeroTelefone("43991368486")
                .statusEnvio(StatusEnvioEnum.RECEBIDO)
                .dataEnvio(LocalDateTime.of(2026, 9, 25, 15, 30))
                .build();

        smsRequestDTO = SmsRequestDTO.builder()
                .id(1L)
                .numeroTelefone("43991368486")
                .statusEnvio(StatusEnvioEnum.RECEBIDO)
                .dataEnvio(LocalDateTime.of(2026, 9, 25, 15, 30))
                .build();

        smsResponseDTO = SmsResponseDTO.builder()
                .id(1L)
                .numeroTelefone("43991368486")
                .statusEnvio(StatusEnvioEnum.RECEBIDO)
                .dataEnvio(LocalDateTime.of(2026, 9, 25, 15, 30))
                .build();

        listaEntidade = List.of(smsMensagemEntity);
        listaResponse = List.of(smsResponseDTO);
    }

    @Test
    void deveAlterarStatusSms () {
        when(repository.findById(smsRequestDTO.getId())).thenReturn(Optional.of(smsMensagemEntity));
        when(repository.save(smsMensagemEntity)).thenReturn(smsMensagemEntity);
        when(converter.paraDTO(smsMensagemEntity)).thenReturn(smsResponseDTO);

        SmsResponseDTO resultado = service.alteraStatusSms(smsRequestDTO.getId(), smsRequestDTO.getStatusEnvio());

        assertEquals(smsResponseDTO, resultado);
    }

    @Test
    void naoDeveAlterarStatusSms () {
        assertThrows(resourcesNotFoundExceptions.getClass(), () -> service.alteraStatusSms(99L, smsRequestDTO.getStatusEnvio()));
    }

    @Test
    void deveGerarRelatorio () {
        when(clock.instant()).thenReturn(agora);
        when(clock.getZone()).thenReturn(ZoneOffset.UTC);
        when(repository.findByStatusEnvioAndDataEnvioAfter(smsRequestDTO.getStatusEnvio(),limiteEsperado)).thenReturn(listaEntidade);
        when(converter.paraListaResponse(listaEntidade)).thenReturn(listaResponse);

        List<SmsResponseDTO> resultado = service.gerarRelatorio(smsRequestDTO.getStatusEnvio());

        assertEquals(listaResponse, resultado);
    }


}
