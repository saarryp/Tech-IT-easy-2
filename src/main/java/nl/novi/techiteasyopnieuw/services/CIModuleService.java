package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.controllers.CIModuleController;
import nl.novi.techiteasyopnieuw.dto.cimodule.CIModuleDto;
import nl.novi.techiteasyopnieuw.models.CIModule;
import nl.novi.techiteasyopnieuw.repositories.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

public CIModuleDto createCIModule(CIModule createCIModuleDto) {
        CIModule ciModule = new CIModule();
       ciModule.setId(createCIModuleDto.id);
        ciModule.setName(createCIModuleDto.name);
        ciModule.setPrice(createCIModuleDto.price);
        ciModule.setType(createCIModuleDto.type);

        CIModule.save(ciModule);
        createCIModuleDto.id = ciModule.getId();
        return createCIModuleDto;
}

}
