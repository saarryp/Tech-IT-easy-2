package nl.novi.techiteasyopnieuw.controllers;


import nl.novi.techiteasyopnieuw.dto.id.IdInputDto;
import nl.novi.techiteasyopnieuw.dto.television.TelevisionDto;
import nl.novi.techiteasyopnieuw.dto.television.TelevisionInputDto;
import nl.novi.techiteasyopnieuw.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("televisions")
public class TelevisionController {


   private final TelevisionService televisionService;

   @Autowired
    public TelevisionController(TelevisionService televisionService) {

       this.televisionService = televisionService;
    }


    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(){

        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getOneTelevision(@PathVariable("id") Long id){
       TelevisionDto televisionDto = televisionService.getTelevisionById(id);
        return ResponseEntity.ok(televisionDto);
    }


    //deze ombouwen
    @PostMapping
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {

            televisionService.deleteOneTelevision(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto>updateTelevision(@PathVariable("id") Long id,
                                                         @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto updatedTelevision = televisionService.updateTelevisions(id, televisionInputDto);
        return ResponseEntity.ok(updatedTelevision);
    }


    @PutMapping("/televisions/{id}/remotecontroller")
    public  ResponseEntity<Object>assignRemoteControllerToTelevision(@PathVariable("id") Long id,
                                                         @Valid @RequestBody IdInputDto input){
      televisionService.assignRemoteControllerToTelevision(id, input.id);
      return ResponseEntity.noContent().build();

    }
}
