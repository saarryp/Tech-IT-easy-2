package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.dto.TelevisionDto;
import nl.novi.techiteasyopnieuw.exceptions.RecordNotFoundException;
import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository repos;
//constructor toevoegen om dependency injection uit te voeren


    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    //functie ophalen van alle tv' + pas de Controller aan
    //funtie ophalen 1  + pas de getmethode aan en maak een nieuwe methode in de service
    //functie opslaan 1 tv
    //functie verwijderen 1 tv
    //functie updaten 1 tv

    //alletv method

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = repos.findAll();
        List<TelevisionDto> tvListDto = new ArrayList<>();
        //de nieuwe arraylist kan nu de nieuwe lijst opbergen in de DTO objects
        for (Television t : tvList) {
            tvListDto.add(televisionToTelevisionDto(t));
        }
        return tvListDto;
    }

    public TelevisionDto createTelevision(TelevisionDto createTelevisionDto) {
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

    public TelevisionDto televisionToTelevisionDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.id = television.getId();
        televisionDto.hdr = television.getHdr();
        televisionDto.brand = television.getBrand();
        televisionDto.price = television.getPrice();
        televisionDto.availableSize = television.getAvailableSize();
        televisionDto.refreshRate = television.getRefreshRate();
        televisionDto.screenType = television.getScreenType();
        televisionDto.screenQuality = television.getScreenQuality();
        televisionDto.smartTv = television.getSmartTv();
        televisionDto.wifi = television.getWifi();
        televisionDto.voiceControl = television.getVoiceControl();
        televisionDto.hdr = television.getHdr();
        televisionDto.bluetooth = television.getBluetooth();
        televisionDto.ambiLight = television.getAmbiLight();
        televisionDto.originalStock = television.getOriginalStock();
//            televisionDto.sold = television.getSold();
//            televisionDto.dateOfSell = television.getDateOfSell();
//            televisionDto.dateOfPurchase = television.getDateOfPurchase();


        return televisionDto;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> tv = repos.findById(id);
        if (tv.isPresent()) {
            return televisionToTelevisionDto(tv.get());
        } else {
            throw new RecordNotFoundException("tv with id :" + id + " not found");
        }
    }

//
public void deleteOneTelevision(Long id) {
    try {
        repos.deleteById(id);
    } catch (EmptyResultDataAccessException ex) {
        throw new RecordNotFoundException("TV with id: " + id + " not found");
    }
}

//nu een functie voor updaten van de televisies



}
