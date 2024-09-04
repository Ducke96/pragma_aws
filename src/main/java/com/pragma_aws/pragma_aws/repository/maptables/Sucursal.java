package com.pragma_aws.pragma_aws.repository.maptables;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sucursal")
public class Sucursal {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsucursal")
    private int idSucursal;

    @Column(name = "nombre")
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "idfranquicia")
    private Franquicia franquicia;

    @OneToMany(mappedBy = "sucursal")
    private List<Producto> productos;
    
}
