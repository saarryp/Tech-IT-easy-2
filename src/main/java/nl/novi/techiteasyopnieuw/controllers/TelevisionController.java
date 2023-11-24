package nl.novi.techiteasyopnieuw.controllers;


import nl.novi.techiteasyopnieuw.models.Television;
import nl.novi.techiteasyopnieuw.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class TelevisionController {


    private final TelevisionRepository televisionRepository;
    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    @GetMapping("/televisions")
    public ResponseEntity<Object> getAllTelevisions(){

        return ResponseEntity.ok("televisions");
    }

    @GetMapping("televisions/{id}")
    public ResponseEntity<Object> getOneTelevision(@PathVariable("id") Long id){
        Optional<Television> savedTelevision = televisionRepository.findById(id);
        return ResponseEntity.ok(savedTelevision.get());
    }


    //deze ombouwen
    @PostMapping("/televisions")
    public ResponseEntity<Television> addTelevision(@RequestBody Television television){

        Television savedTelevision = televisionRepository.save(television);
        return ResponseEntity.created(null).body(savedTelevision);
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {

        return ResponseEntity.noContent().build();
    }

}
