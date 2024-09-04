
package com.pragma_aws.pragma_aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pragma_aws.pragma_aws.repository.FranquiciaRepository;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;
import com.pragma_aws.pragma_aws.repository.maptables.Producto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.controller.dto.FranquiciaDTO;
import com.pragma_aws.pragma_aws.controller.dto.ProductoDTO;

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
         if (!optionalFranquicia.isPresent()) {
        return new ResponseEntity<>("Franquicia No encontrada", HttpStatus.NOT_FOUND);
        }
        FranquiciaDTO franquicia = new FranquiciaDTO(optionalFranquicia.get());
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
public ResponseEntity<Object> saveFranquicia(@RequestBody FranquiciaDTO franquiciaDTO) {
    Franquicia franquicia = new Franquicia();
    if(franquiciaDTO.getNombre()==null || franquiciaDTO.getNombre().isEmpty()){
        return new ResponseEntity<>("nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
    }
    franquicia.setNombre(franquiciaDTO.getNombre());   
    Franquicia savedFranquicia = facadeFranquicia.saveFranquicia(franquicia);
    FranquiciaDTO savedFranquiciaDTO = new FranquiciaDTO(savedFranquicia);
    
    return new ResponseEntity<>(savedFranquiciaDTO, HttpStatus.CREATED);
}
@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteFranquicia(@PathVariable int id) {
    boolean isRemoved = facadeFranquicia.deleteFranquicia(id);
    if (isRemoved) {
        return new ResponseEntity<>("Franquicia eliminada",HttpStatus.NO_CONTENT);
    } else {
        return new ResponseEntity<>("Franquicia no encontrada", HttpStatus.NOT_FOUND);
    }
}


@PatchMapping
public ResponseEntity<Object> updateFranquicia(@RequestBody FranquiciaDTO franquiciaDTO) {
   
    Franquicia franquicia = new Franquicia();
    if(franquiciaDTO.getNombre()==null || franquiciaDTO.getNombre().isEmpty()){
        return new ResponseEntity<>("nombre no puede estar vacio", HttpStatus.BAD_REQUEST);
    }
    Optional<Franquicia> optionalFranquicia =facadeFranquicia.getFranquicia(franquiciaDTO.getId());
    if (!optionalFranquicia.isPresent()) {
    
        return new ResponseEntity<>("Franquicia no encontrda", HttpStatus.NOT_FOUND);
    }

franquicia = optionalFranquicia.get();
franquicia.setNombre(franquiciaDTO.getNombre());
Franquicia savedFranquicia = facadeFranquicia.saveFranquicia(franquicia);
FranquiciaDTO franquiciaDTOSave = new FranquiciaDTO(savedFranquicia);

return new ResponseEntity<>(franquiciaDTOSave, HttpStatus.OK);
}

}
