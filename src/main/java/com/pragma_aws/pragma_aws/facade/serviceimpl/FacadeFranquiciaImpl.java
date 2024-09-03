package com.pragma_aws.pragma_aws.facade.serviceimpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pragma_aws.pragma_aws.facade.FacadeFranquicia;
import com.pragma_aws.pragma_aws.repository.FranquiciaRepository;
import com.pragma_aws.pragma_aws.repository.maptables.Franquicia;

@Service
public class FacadeFranquiciaImpl implements FacadeFranquicia{
    private FranquiciaRepository franquiciaRepository;

    @Autowired
    public FacadeFranquiciaImpl(FranquiciaRepository franquiciaRepository){
        this.franquiciaRepository = franquiciaRepository;
    }

    @Override
    public Franquicia saveFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    @Override
    public Optional<Franquicia> getFranquicia(int id) {
        return franquiciaRepository.findById(id);
    }

    @Override
    public List<Franquicia> getFranquicias() {
           return StreamSupport.stream(franquiciaRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteFranquicia(int id) {
        if (franquiciaRepository.existsById(id)) {
            franquiciaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

