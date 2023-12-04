package nl.novi.techiteasyopnieuw.controllers;


import nl.novi.techiteasyopnieuw.dto.TelevisionDto;
import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.repositories.TelevisionRepository;
import nl.novi.techiteasyopnieuw.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
public class TelevisionController {


   private final TelevisionService televisionService;
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }


    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions(){

        return ResponseEntity.ok("televisions");
    }

//    @GetMapping("televisions/{id}")
//    public ResponseEntity<Object> getOneTelevision(@PathVariable("id") Long id){
//        Optional<Television> savedTelevision = televisionRepository.findById(id);
//        return ResponseEntity.ok(savedTelevision.get());
//    }


    //deze ombouwen
    @PostMapping("/televisions")
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionDto televisionDto){

        TelevisionDto savedTelevision = televisionService.createTelevision(televisionDto);

        //toevoegen Uri zodat je in de header in postman deze terug kan vinden
        //bij elke postmapping is onderstaande Uri aanroep gebruikelijk

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedTelevision.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedTelevision);
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {

        return ResponseEntity.noContent().build();
    }

}
