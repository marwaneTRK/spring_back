package com.example.demo.api;

import com.example.demo.entity.Tache;
import com.example.demo.repository.TacheRepository;
import com.example.demo.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/tache")
public class TacheController {
    @Autowired
    private TacheService tacheService;

    @Autowired
    private TacheRepository tacheRepository;

    @GetMapping("/")
    public ResponseEntity<List<Tache>> getAllTaches() {
        return tacheService.findAllTaches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTacheById(@PathVariable("id") Long tacheId) {
        return tacheService.findTacheById(tacheId);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteTacheById(@PathVariable("id") Long tacheId) {
        return tacheService.deleteTacheById(tacheId);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTache(@RequestBody Tache tache) {
        return tacheService.addTache(tache);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Tache> updateTache(@PathVariable("id") Long tacheId, @RequestBody Tache tache) {
        return tacheService.updateTache(tacheId, tache);
    }
}
