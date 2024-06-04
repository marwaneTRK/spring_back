package com.example.demo.api;

import com.example.demo.entity.Presence;
import com.example.demo.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/presence")
public class PresenceController {
    @Autowired
    private PresenceService presenceService;

    @GetMapping("/presences")
    public List<Presence> getPresences() {
        return presenceService.findAllPresences();
    }

    @GetMapping("/presences/{id}")
    public ResponseEntity<?> getPresence(@PathVariable("id") Long presenceId) {
        return presenceService.findPresenceById(presenceId);
    }

    @DeleteMapping("/presences/{id}/delete")
    public ResponseEntity<String> deletePresence(@PathVariable("id") Long presenceId) {
        return presenceService.deletePresenceById(presenceId);
    }

    @PostMapping("/presences")
    public ResponseEntity<String> createPresence(@RequestBody Presence presence) {
        return presenceService.addPresence(presence);
    }

    @PutMapping("/presences/{id}/update")
    public ResponseEntity<Presence> updatePresence(@PathVariable("id") Long presenceId, @RequestBody Presence presence) {
        return presenceService.updatePresence(presenceId, presence);
    }
    @PostMapping("/presences/{employeeId}/punch-in")
    public ResponseEntity<String> punchIn(@PathVariable("employeeId") Long employeeId) {
        return presenceService.punchIn(employeeId);
    }

    @PostMapping("/presences/{presenceId}/punch-out")
    public ResponseEntity<String> punchOut(@PathVariable("presenceId") Long presenceId) {
        return presenceService.punchOut(presenceId);
    }
}
