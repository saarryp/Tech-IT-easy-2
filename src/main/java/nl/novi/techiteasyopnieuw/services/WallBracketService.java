package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.dto.wallbracket.WallBracketDto;
import nl.novi.techiteasyopnieuw.models.WallBracket;
import nl.novi.techiteasyopnieuw.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<WallBracketDto> dtos= new ArrayList<>();
        for (WallBracket w : wallBracketList) {
           dtos.add(transferToDto(w));
        }
        return dtos;
    }

    private WallBracketDto transferToDto(WallBracket wallBracket){
        return null;
    }
}
