package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Presence;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PresenceService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PresenceRepository presenceRepository;

    public List<Presence> findAllPresences() {
        return presenceRepository.findAll();
    }

    public ResponseEntity<?> findPresenceById(Long id) {
        Optional<Presence> presenceOptional = presenceRepository.findById(id);
        if (presenceOptional.isPresent()) {
            Presence presence = presenceOptional.get();
            return ResponseEntity.ok(presence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deletePresenceById(Long id) {
        Optional<Presence> presenceOptional = presenceRepository.findById(id);
        if (presenceOptional.isPresent()) {
            presenceRepository.delete(presenceOptional.get());
            return ResponseEntity.ok("Deleted Succefully");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> addPresence(Presence presence) {
        presenceRepository.save(presence);
        return ResponseEntity.ok("Presence added successfully");
    }

    public ResponseEntity<Presence> updatePresence(Long presenceId, Presence presence) {
        Optional<Presence> optionalPresence = presenceRepository.findById(presenceId);
        if (optionalPresence.isPresent()) {
            Presence presenceUpdated = optionalPresence.get();
            presenceUpdated.setPunchIn(presence.getPunchIn());
            presenceUpdated.setPunchOut(presence.getPunchOut());
            presenceUpdated.setWorkHours(presence.getWorkHours());
            presenceRepository.save(presenceUpdated);
            return ResponseEntity.ok(presenceUpdated);
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<String> punchIn(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Presence presence = new Presence();
            presence.setEmployee(employeeOptional.get());
            presence.setPunchIn(LocalDateTime.now());
            presenceRepository.save(presence);
            return ResponseEntity.ok("Punch in successful");
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> punchOut(Long presenceId) {
        Optional<Presence> presenceOptional = presenceRepository.findById(presenceId);
        if (presenceOptional.isPresent()) {
            Presence presence = presenceOptional.get();
            if (presence.getPunchOut() != null) {
                return ResponseEntity.badRequest().body("Already punched out");
            }
            presence.setPunchOut(LocalDateTime.now());
            Duration duration = Duration.between(presence.getPunchIn(), presence.getPunchOut());
            long hoursWorked = duration.toHours();
            int minutesWorked = (int) ((duration.getSeconds() % 3600) / 60);
            presence.setWorkHours(hoursWorked + (minutesWorked / 60.0));
            presenceRepository.save(presence);
            return ResponseEntity.ok("Punch out successful");
        }
        return ResponseEntity.notFound().build();
    }
}



