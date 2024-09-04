package com.pragma_aws.pragma_aws.controller.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SucursalDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nombre")
    private String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Franquicia")
    private FranquiciaDTO franquicia;

    public SucursalDTO(){}

    public SucursalDTO(Sucursal sucursal){
        this.id = sucursal.getIdSucursal();
        this.nombre = sucursal.getNombre();
        if(sucursal.getFranquicia() != null){
            FranquiciaDTO franquiciadto = new FranquiciaDTO(sucursal.getFranquicia());
            this.franquicia = franquiciadto;
        }else{
            this.franquicia = null;
        }

    }
}
