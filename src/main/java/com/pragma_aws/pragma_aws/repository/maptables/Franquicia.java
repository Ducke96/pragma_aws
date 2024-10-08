package com.pragma_aws.pragma_aws.repository.maptables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.pragma_aws.pragma_aws.repository.maptables.Sucursal;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "franquicia")
public class Franquicia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfranquicia")
    private int idFranquicia;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "franquicia")
    private List<Sucursal> sucursales;

    
}
