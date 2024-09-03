
package com.pragma_aws.pragma_aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pragma_aws.pragma_aws.repository.FranquiciaRepository;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.controller.dto.FranquiciaDTO;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping(value = "franquicia")
@CrossOrigin(origins = "*")
public class FranquiciaController {

    private FacadeFranquicia facadeFranquicia;

        @Autowired
    public FranquiciaController(FacadeFranquicia facadeFranquicia){
        this.facadeFranquicia = facadeFranquicia;
    }
    


    @GetMapping("/{id}")
    public ResponseEntity<Object> getFranquicia(@PathVariable int id) {

        Optional<Franquicia> optionalFranquicia =facadeFranquicia.getFranquicia(id);
        Franquicia franquiciaBd = optionalFranquicia.orElse(new Franquicia());
        FranquiciaDTO franquicia = new FranquiciaDTO(franquiciaBd);
        return new ResponseEntity<>(franquicia,HttpStatus.OK );

    }

    @GetMapping
    public ResponseEntity<List<FranquiciaDTO>> getAllFranquicias() {
    
    List<Franquicia> franquicias = facadeFranquicia.getFranquicias();
    List<FranquiciaDTO> franquiciasDTOs = franquicias.stream()
                                           .map(FranquiciaDTO::new)
                                           .collect(Collectors.toList());
    return new ResponseEntity<>(franquiciasDTOs, HttpStatus.OK);
}
@PostMapping
public ResponseEntity<FranquiciaDTO> saveUsuario(@RequestBody FranquiciaDTO franquiciaDTO) {
    Franquicia franquicia = new Franquicia();
    franquicia.setNombre(franquiciaDTO.getNombre());   
    Franquicia savedFranquicia = facadeFranquicia.saveFranquicia(franquicia);
    FranquiciaDTO savedFranquiciaDTO = new FranquiciaDTO(savedFranquicia);
    
    return new ResponseEntity<>(savedFranquiciaDTO, HttpStatus.CREATED);
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteFranquicia(@PathVariable int id) {
    boolean isRemoved = facadeFranquicia.deleteFranquicia(id);
    if (isRemoved) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
