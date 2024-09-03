
package com.pragma_aws.pragma_aws;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.pragma_aws.pragma_aws.controller.FranquiciaController;
import com.pragma_aws.pragma_aws.controller.dto.FranquiciaDTO;
import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(FranquiciaController.class)
class TestControllerUsuario {

    private MockMvc mockMvc;

    @InjectMocks
    private FranquiciaController FranquiciasController;

    @MockBean
    private FacadeFranquicia facadeFranquicia;  // Mock del FacadeUsuario

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(FranquiciasController).build();
    }

    @Test
    void testGetUserById() throws Exception {
        Franquicia franquicia = new Franquicia(1, "higuera");
        when(facadeFranquicia.getFranquicia(1)).thenReturn(Optional.of(franquicia));  // Simula el comportamiento del FacadeUsuario

        mockMvc.perform(MockMvcRequestBuilders.get("/franquicia/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("higuera"));
                
    }
@Test
void testSaveUser() throws Exception {
    // Crear el objeto Usuario que se espera que sea guardado
    Franquicia franquicia = new Franquicia();
    franquicia.setIdFranquicia(1);
    franquicia.setNombre("pablo");


    // Configurar el mock para devolver el usuario cuando se llame al método saveUsuario
    when(facadeFranquicia.saveFranquicia(any(Franquicia.class))).thenReturn(franquicia);

    // Crear el JSON para enviar en la solicitud POST
    String franquiciaJson = "{\"nombre\":\"pablo\"}";

    // Realizar la solicitud POST con el JSON del usuario
    mockMvc.perform(MockMvcRequestBuilders.post("/franquicia")
            .contentType(MediaType.APPLICATION_JSON)
            .content(franquiciaJson))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("pablo"));
}
@Test
void testGetAllUsers() throws Exception {
    Franquicia franquicia1 = new Franquicia(1, "higuera");
    Franquicia franquicia2 = new Franquicia(2, "perez");
    List<Franquicia> franquicias = Arrays.asList(franquicia1, franquicia2);

    when(facadeFranquicia.getFranquicias()).thenReturn(franquicias);  // Simula el comportamiento del FacadeUsuario

    mockMvc.perform(MockMvcRequestBuilders.get("/franquicia")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("higuera"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre").value("perez"));
}

@Test
void testDeleteUser() throws Exception {
    int franquiciaId = 1;

    // Configura el mock para devolver true cuando se intente eliminar el usuario
    when(facadeFranquicia.deleteFranquicia(franquiciaId)).thenReturn(true);

    // Realiza la solicitud DELETE
    mockMvc.perform(MockMvcRequestBuilders.delete("/franquicia/{id}", franquiciaId))
            .andExpect(MockMvcResultMatchers.status().isNoContent());  // Espera el código de estado 204

    // Puedes añadir más verificaciones si es necesario
}
}
