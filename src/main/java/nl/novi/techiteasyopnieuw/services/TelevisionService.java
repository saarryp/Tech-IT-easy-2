package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.dto.TelevisionDto;
import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {
    private final TelevisionRepository repos;
//constructor toevoegen om dependency injection uit te voeren


    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    //functie ophalen van alle tv's
    //funtie ophalen 1 tv
    //functie oplsaan 1 tv
    //functie verwijderen 1 tv
    //functie updaten 1 tv
    public TelevisionDto createTelevision(TelevisionDto createTelevisionDto){
        //maak een create aan of aparte methode moet overeenkomen met DTO variabelen

        Television television = new Television();
        television.setType(createTelevisionDto.type);
        television.setBrand(createTelevisionDto.brand);
        television.setName(createTelevisionDto.name);
        television.setPrice(createTelevisionDto.price);
        television.setAvailableSize(createTelevisionDto.availableSize);
        television.setRefreshRate(createTelevisionDto.refreshRate);
        television.setScreenType(createTelevisionDto.screenType);
        television.setScreenQuality(createTelevisionDto.screenQuality);
        television.setSmartTv(createTelevisionDto.smartTv);
        television.setWifi(createTelevisionDto.wifi);
        television.setVoiceControl(createTelevisionDto.voiceControl);
        television.setHdr(createTelevisionDto.hdr);
        television.setBluetooth(createTelevisionDto.bluetooth);
        television.setAmbiLight(createTelevisionDto.ambiLight);
        television.setOriginalStock(createTelevisionDto.originalStock);


        //nu opslaan in de repos
         repos.save(television);
         createTelevisionDto.id = television.getId();
         return createTelevisionDto;
}
}
