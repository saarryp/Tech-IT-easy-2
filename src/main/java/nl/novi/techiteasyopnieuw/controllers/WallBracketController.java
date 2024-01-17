package nl.novi.techiteasyopnieuw.controllers;


import nl.novi.techiteasyopnieuw.dto.wallbracket.WallBracketDto;
import nl.novi.techiteasyopnieuw.services.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/wall_brackets")

public class WallBracketController {

    private WallBracketService wallBracketService;

    @Autowired
    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }


    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getOneWallBracket(@PathVariable("id") Long id) {
        WallBracketDto wallBracketDto = wallBracketService.getWallBracketById(id);
        return ResponseEntity.ok(wallBracketDto);
    }

    @PostMapping
    public ResponseEntity<WallBracketDto> addWallBracket(@RequestBody WallBracketDto wallBracketDto) {
        WallBracketDto savedWallBracket = wallBracketService.createWallBracket(wallBracketDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedWallBracket.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedWallBracket);
    }

}
