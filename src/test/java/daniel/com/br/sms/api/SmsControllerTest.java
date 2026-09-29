package daniel.com.br.sms.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daniel.com.br.sms.api.controller.SmsController;
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
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SmsControllerTest {

    @InjectMocks
    SmsController controller;

    @Mock
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

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    SmsMensagemEntity smsMensagemEntity;


    SmsRequestDTO smsRequestDTO;


    SmsResponseDTO smsResponseDTO;

    List<SmsMensagemEntity> listaEntidade;

    List<SmsResponseDTO> listaResponse;

    private MockMvc mockMvc;

    private String url;

    private String json;

    @BeforeEach
    public void setup () throws JsonProcessingException {

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

        ReflectionTestUtils.setField(controller, "service", service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/sms";

        json = objectMapper.writeValueAsString(smsRequestDTO);
    }

    @Test
    void deveSalvarMensagemSms () throws Exception {
        when(service.salvarMensagemSms(smsRequestDTO)).thenReturn(smsResponseDTO);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());

        verify(service).salvarMensagemSms(smsRequestDTO);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveDeletarSms () throws Exception {
        doNothing().when(service).deletaSms(smsRequestDTO.getId());

        mockMvc.perform(delete(url+"/deletar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(smsRequestDTO.getId()))
        ).andExpect(status().isOk());
        verify(service).deletaSms(smsRequestDTO.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveAtualizarDadosSms () throws Exception {
        when(service.alterarDadosSms(smsRequestDTO.getId(), smsRequestDTO)).thenReturn(smsResponseDTO);

        mockMvc.perform(put(url+"/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(smsRequestDTO.getId()))
                .content(json)
        ).andExpect(status().isOk());

        verify(service).alterarDadosSms(smsRequestDTO.getId(), smsRequestDTO);
        verifyNoMoreInteractions(service);
    }



    @Test
    void deveAlterarStatusMensagem () throws Exception {
       when(service.alteraStatusSms(smsRequestDTO.getId(), smsRequestDTO.getStatusEnvio())).thenReturn(smsResponseDTO);

       mockMvc.perform(put(url+"/status")
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .param("id", String.valueOf(smsRequestDTO.getId()))
               .param("statusEnvioEnum", String.valueOf(smsRequestDTO.getStatusEnvio()))
       ).andExpect(status().isOk());

       verify(service).alteraStatusSms(smsRequestDTO.getId(), smsRequestDTO.getStatusEnvio());
       verifyNoMoreInteractions(service);

    }

    @Test
    void deveGerarRelatorioSms() throws Exception {
        when(service.gerarRelatorio(smsRequestDTO.getStatusEnvio())).thenReturn(listaResponse);

        mockMvc.perform(get(url+"/relatorio")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("statusEnvioEnum", String.valueOf(smsRequestDTO.getStatusEnvio()))
        ).andExpect(status().isOk());

        verify(service).gerarRelatorio(smsRequestDTO.getStatusEnvio());
        verifyNoMoreInteractions(service);

    }





}
