package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.dto.wallbracket.WallBracketDto;
import nl.novi.techiteasyopnieuw.dto.wallbracket.WallBracketInputDto;
import nl.novi.techiteasyopnieuw.exceptions.RecordNotFoundException;
import nl.novi.techiteasyopnieuw.models.WallBracket;
import nl.novi.techiteasyopnieuw.repositories.WallBracketRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepos;

    //constructor voor dependency injection voor wallbracketRepository

    public WallBracketService(WallBracketRepository wallBracketRepos){

        this.wallBracketRepos = wallBracketRepos;
    }

    //functie ophalen van alle remotes + pas controller aan
    //functie ophalen 1 remote
    //functie ophalen opslaan 1 remote
    //functie verwijderen

    //nu alle wallbrackets ophalen en naar dto omzetten



    public List<WallBracketDto> getAllWallBrackets(){
        List<WallBracket> wallBracketList = wallBracketRepos.findAll();
        List<WallBracketDto> wallBracketDtoList= new ArrayList<>();
        for (WallBracket w : wallBracketList) {
           wallBracketDtoList.add(wallBracketToWallBracketDto(w));
        }
        return wallBracketDtoList;
    }

//

   public WallBracketDto createWallBracket(WallBracketDto createWallBracketDto){
        WallBracket wallBracket = new WallBracket();
        wallBracket.setId(wallBracket.getId());
        wallBracket.setSize(wallBracket.getSize());
        wallBracket.setAdjustable(wallBracket.getAdjustable());
        wallBracket.setName(wallBracket.getName());
        wallBracket.setPrice(wallBracket.getPrice());


        wallBracketRepos.save(wallBracket);
        createWallBracketDto.id = wallBracket.getId();
        return createWallBracketDto;
    }

    public WallBracketDto wallBracketToWallBracketDto(WallBracket wallBracket){
        WallBracketDto wallBracketDto = new WallBracketDto();
        wallBracketDto.id = wallBracket.getId();
        wallBracketDto.size = wallBracket.getSize();
        wallBracketDto.adjustable = wallBracket.getAdjustable();
        wallBracketDto.name= wallBracket.getName();
        wallBracketDto.price = wallBracket.getPrice();

        return wallBracketDto;
    }

    public WallBracketDto getWallBracketById(Long id) {
        Optional<WallBracket> wallbrckt = wallBracketRepos.findById(id); {
            if (wallbrckt.isPresent()) {
                return wallBracketToWallBracketDto(wallbrckt.get());
            } else {
                throw new RecordNotFoundException("WallBracket with id: " + id + "not found");
            }
        }
    }

    public void deleteOneWallBracket(Long id) {
        try {
            wallBracketRepos.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("Wallbracket with id " + id + "not found");
        }
    }

    public WallBracketDto updateWallBrackets(Long id, WallBracketInputDto wallBracketInputDto) {
        Optional<WallBracket> existingWallBracket = wallBracketRepos.findById(id);


        if (existingWallBracket.isPresent()) {
            ;
            WallBracket updatedWallBracket = existingWallBracket.get();

            updatedWallBracket.setAdjustable(wallBracketInputDto.adjustable);
            updatedWallBracket.setSize(wallBracketInputDto.size);
            updatedWallBracket.setName(wallBracketInputDto.name);
            updatedWallBracket.setPrice(wallBracketInputDto.price);

            wallBracketRepos.save(updatedWallBracket);

            return wallBracketToWallBracketDto(updatedWallBracket);
        } else {
            throw new RecordNotFoundException(" Wallbracket with id " + id + "is not found");
        }
    }

}
