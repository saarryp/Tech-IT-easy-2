package nl.novi.techiteasyopnieuw.services;

import nl.novi.techiteasyopnieuw.dto.television.TelevisionDto;
import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.repositories.TelevisionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

//eerst een relatie maken met mockito door een @extend
@ExtendWith(MockitoExtension.class)
class TelevisionServiceTest {

    //repository gebruiken door een injectmocks (niet injecten maar mocken want alleen testen of het daar  zo los mogelijk een test doen vanwege de dependencies die je los wil houden
   @Mock
    TelevisionRepository televisionRepository;

   //service wordt geinjecteerd met de mock van hierboven
    @InjectMocks
    TelevisionService televisionService;

//annotatie test en annotatie wat we willen testen
    @Test
    //wat willen we testen
    @DisplayName("should get all tv's")

    void getAllTelevisions() {

        //ARRANGE
        //een televisie aanmaken om deze te kunnen testen setters gebruiken (want de televisie heeft geen waarden)
        //vervolgens zelf een waarde aan de id toevoegen wel met L want is een long, mag willekeurig zijn
        Television television = new Television();
        television.setId(1L);
        television.setName(("LG"));
        television.setPrice(400.00);

        //wanneer de repository iets vindt van de getAllTelevisions dan graag het volgende teruggeven
        when(televisionRepository.findAll()).thenReturn(List.of(television));

        //ACT
        //service aanroepen (getalltelevisions) methode bekijken die je wil aanroepen.ACT voert uit
        List<TelevisionDto> result = televisionService.getAllTelevisions();

        for (TelevisionDto dto : result) {
            System.out.println("TelevisionDto: " + dto); // Log the entire object
        }



        //ASSERT


        //je telt vanaf 0 maar je geeft id 1//kijken wat we verwachten en wat de servicelaag opslaat overeenkomt dus in dit geval onder ACT geformuleerde result, dan een id meegveen aan de get
        //
        assertEquals(1L, result.get(0).id);
        assertEquals("LG", result.get(0).name);
        assertEquals(400.00, result.get(0). price);

    }


    @Test
    @DisplayName("should delete tv")

    public void deleteOneTelevision(){
        //ARRANGE

        //aanmaken van een television, want television nodig om te deleten

        Television television = new Television();
        television.setId(1L);
        television.setName(("LG"));
        television.setPrice(400.00);

        //findById toepassen en anyLong toevoegen

        when(televisionRepository.findById(anyLong())).thenReturn(Optional.of(television));


        //ACT

        //bij de act findby halen om een tv op te halenen te kunnen deleten (dus dezelfde als bij getAllTv), maar dan geen List want je vraagt eentje op bij FindById dus de List moet weg en direct DTO opvragen




        televisionService.deleteOneTelevision(1L);

        TelevisionDto result = televisionService.getTelevisionById(1L);

        //ASSERT


        assertEquals(1, result.id);

    }
}