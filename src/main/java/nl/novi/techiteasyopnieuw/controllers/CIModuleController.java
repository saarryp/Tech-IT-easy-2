package nl.novi.techiteasyopnieuw.controllers;


import nl.novi.techiteasyopnieuw.dto.cimodule.CIModuleDto;
import nl.novi.techiteasyopnieuw.dto.cimodule.CIModuleInputDto;
import nl.novi.techiteasyopnieuw.dto.television.TelevisionDto;
import nl.novi.techiteasyopnieuw.dto.television.TelevisionInputDto;
import nl.novi.techiteasyopnieuw.models.CIModule;
import nl.novi.techiteasyopnieuw.services.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    @Autowired
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getOneCIModule(@PathVariable("id") Long id) {
        CIModuleDto ciModuleDto = ciModuleService.getCIModuleById(id);
        return ResponseEntity.ok(ciModuleDto);
    }

    @PostMapping
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto savedCIModule =  ciModuleService.createCIModule(ciModuleInputDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedCIModule.id)
                        .toUriString());

        return ResponseEntity.created(uri).body(savedCIModule);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable Long id) {
        ciModuleService.deleteOneCIModule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto>updateCIModule(@PathVariable("id") Long id,
                                                     @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto updatedCIModule = ciModuleService.updateCIModule(id, ciModuleInputDto);
        return ResponseEntity.ok(updatedCIModule);
    }
}
