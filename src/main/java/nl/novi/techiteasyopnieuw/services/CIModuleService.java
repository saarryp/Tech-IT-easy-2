package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.controllers.CIModuleController;
import nl.novi.techiteasyopnieuw.dto.cimodule.CIModuleDto;
import nl.novi.techiteasyopnieuw.dto.cimodule.CIModuleInputDto;
import nl.novi.techiteasyopnieuw.exceptions.RecordNotFoundException;
import nl.novi.techiteasyopnieuw.models.CIModule;
import nl.novi.techiteasyopnieuw.repositories.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CIModuleService {

    //repos ophalen
    private final CIModuleRepository ciModuleRepository;

    //autowired of injection


    public CIModuleService(CIModuleRepository ciModuleRepository){
        this.ciModuleRepository = ciModuleRepository;
    }

    //lijst alle CImodules ophalen

    public List<CIModuleDto>getAllCIModules() {
        List<CIModule> ciModulesList = ciModuleRepository.findAll();
        List<CIModuleDto> ciMListDto = new ArrayList<>();
        for (CIModule c : ciModulesList) {
            ciMListDto.add(transferToCIModuleDto(c));
        }
        return ciMListDto;
    }

public CIModuleDto createCIModule(CIModuleInputDto createCIModuleDto) {
        CIModule ciModule = new CIModule();
//        ciModule.setId(createCIModuleDto.id);
        ciModule.setName(createCIModuleDto.name);
        ciModule.setPrice(createCIModuleDto.price);
        ciModule.setType(createCIModuleDto.type);

//        CIModule.save(ciModule);
     ciModuleRepository.save(ciModule);
//    createCIModuleDto.id = ciModule.getId();
        return transferToCIModuleDto(ciModule);
}

public CIModuleDto transferToCIModuleDto(CIModule ciModule) {
        CIModuleDto ciModuleDto = new CIModuleDto();
    ciModuleDto.id = ciModule.getId();
    ciModuleDto.name = ciModule.getName();
    ciModuleDto.price = ciModule.getPrice();
    ciModuleDto.type = ciModule.getType();
    return ciModuleDto;
}

public CIModuleDto getCIModuleById(Long id) {
    Optional<CIModule> moduleCI = ciModuleRepository.findById(id);
    if(moduleCI.isPresent()) {
        return transferToCIModuleDto(moduleCI.get());
    }
        else {
            throw new RecordNotFoundException("CiModule with id : " + id + "is not found.");
        }
    }

    public CIModuleDto getOneCIModuleById(Long id) {
        Optional<CIModule> moduleCI = ciModuleRepository.findById(id);
        if (moduleCI.isPresent()) {
            return transferToCIModuleDto(moduleCI.get());
        } else {
            throw new RecordNotFoundException("Ci-module with id" + id + "was not found");
        }
    }


    public void deleteOneCIModule(Long id){
            try {
                ciModuleRepository.deleteById(id);
            } catch (EmptyResultDataAccessException ex) {
                throw new RecordNotFoundException("TV with id: " + id + " not found");
            }
        }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto ciModuleInputDto) {
        Optional<CIModule> existingCIModule = ciModuleRepository.findById(id);
        if(existingCIModule.isPresent()) {
            CIModule updateCIModule = existingCIModule.get();
            updateCIModule.setId(ciModuleInputDto.id);
            updateCIModule.setName(ciModuleInputDto.name);
            updateCIModule.setType(ciModuleInputDto.type);
            updateCIModule.setPrice(ciModuleInputDto.price);

            ciModuleRepository.save(updateCIModule);

            return transferToCIModuleDto(updateCIModule);
        } else {
            throw new RecordNotFoundException("Cimodule with id " + id + "is not found");
            }
        }

    }