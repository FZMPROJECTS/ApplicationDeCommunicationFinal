package org.example.applicationdecommunication.Controllers;

import org.example.applicationdecommunication.Services.TwilioVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/video")
public class TwilioVideoController {

    private final TwilioVideoService videoService;

    public TwilioVideoController(TwilioVideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * Endpoint pour obtenir un token d'accès vidéo
     *
     * @param identity Nom unique de l'utilisateur
     * @param roomName Nom de la salle de réunion
     * @return Token JWT pour accéder à la salle
     */
    @GetMapping("/token")
    public ResponseEntity<String> getVideoToken(@RequestParam String identity, @RequestParam String roomName) {
        String token = videoService.generateAccessToken(identity, roomName);
        return ResponseEntity.ok(token);
    }

    // Classe interne pour représenter la requête JSON

}
