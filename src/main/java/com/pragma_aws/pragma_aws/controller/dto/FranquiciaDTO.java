package com.pragma_aws.pragma_aws.controller.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class FranquiciaDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nombre")
    private String nombre;

    public FranquiciaDTO(){}

    public FranquiciaDTO(Franquicia franquicia){
        this.id = franquicia.getIdFranquicia();
        this.nombre = franquicia.getNombre();

    }

}
