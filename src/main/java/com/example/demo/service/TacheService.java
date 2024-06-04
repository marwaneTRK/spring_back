package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Tache;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<List<Tache>> findAllTaches() {
        List<Tache> taches = tacheRepository.findAll();
        return ResponseEntity.ok(taches);
    }

    public ResponseEntity<?> findTacheById(Long id) {
        Optional<Tache> tacheOptional = tacheRepository.findById(id);
        if (tacheOptional.isPresent()) {
            Tache tache = tacheOptional.get();
            return ResponseEntity.ok(tache);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteTacheById(Long id) {
        Optional<Tache> tacheOptional = tacheRepository.findById(id);
        if (tacheOptional.isPresent()) {
            tacheRepository.delete(tacheOptional.get());
            return ResponseEntity.ok("Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> addTache(Tache tache) {
            tacheRepository.save(tache);
            return new ResponseEntity<>("Tache created successfully.", HttpStatus.CREATED);
    }

    public ResponseEntity<Tache> updateTache(Long tacheId, Tache tache) {
        Optional<Tache> optionalTache = tacheRepository.findById(tacheId);
        if (optionalTache.isPresent()) {
            Tache tacheUpdated = optionalTache.get();
            tacheUpdated.setName(tache.getName());
            tacheUpdated.setDescription(tache.getDescription());
            tacheUpdated.setDateDebut(tache.getDateDebut());
            tacheUpdated.setDateFin(tache.getDateFin());
            tacheUpdated.setStatus(tache.getStatus());
            tacheRepository.save(tacheUpdated);
            return ResponseEntity.ok(tacheUpdated);
        }
        return ResponseEntity.notFound().build();
    }
}
