// package odonto.controller;

// import static org.hamcrest.Matchers.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.time.LocalDateTime;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import odonto.model.Consulta;
// import odonto.services.ConsultaService;

// @WebMvcTest(ConsultaController.class)
// class ConsultaControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Mock
//     private ConsultaService consultaService;

//     @InjectMocks
//     private ConsultaController consultaController;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         mockMvc = MockMvcBuilders.standaloneSetup(consultaController).build();
//     }

    // @Test
    // void testAgendarConsulta() throws Exception {
    //     Consulta consulta = new Consulta();
    //     consulta.setId(1L);
    //     consulta.setDataHora(LocalDateTime.now());

    //     when(consultaService.agendarConsulta(any(Consulta.class))).thenReturn(consulta);
    //     mockMvc.perform(post("/consultas")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(consulta)))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.id", is(1)));
    // }

//     @Test
//     void testCancelarConsulta() throws Exception {

//         Long consultaId = 1L;

//         doNothing().when(consultaService).cancelarConsulta(consultaId);
//         mockMvc.perform(delete("/consultas/{id}", consultaId))
//                 .andExpect(status().isOk());

//         verify(consultaService, times(1)).cancelarConsulta(consultaId);
//     }
// }
