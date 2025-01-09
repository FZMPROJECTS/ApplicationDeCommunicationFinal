package org.example.applicationdecommunication.Services;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioVideoService {

    @Value("${twilio.account.sid2}")
    private String accountSid;

    @Value("${twilio.auth.token2}")
    private String authToken;

    @Value("${twilio.api.key.sid2}")
    private String apiKeySid;

    @Value("${twilio.api.key.secret2}")
    private String apiKeySecret;

    /**
     * Génère un token d'accès pour une salle de vidéo.
     *
     * @param identity Le nom unique de l'utilisateur
     * @param roomName Le nom de la salle de réunion
     * @return Un token JWT pour l'utilisateur
     */
    public String generateAccessToken(String identity, String roomName) {
        // Créer un grant vidéo
        VideoGrant grant = new VideoGrant();
        grant.setRoom(roomName);

        // Créer un token d'accès
        long expirationTimeInSeconds = 3600L; // 1 heure

        // Créer un token d'accès avec un expiration en secondes (en incluant l'expiration dans le payload JWT)
        AccessToken accessToken = new AccessToken.Builder(
                accountSid,  // SID du compte Twilio
                apiKeySid,   // SID de l'API Key
                apiKeySecret // Secret de l'API Key
        ).identity(identity)  // Identité de l'utilisateur
                .grant(grant)        // Grant vidéo
                .build();

        // L'expiration du token est par défaut gérée par Twilio.
        // Si vous souhaitez la gérer explicitement, vous pouvez la contrôler directement dans le code client ou serveur.

        // Affichage de l'expiration du token
        System.out.println("Le token expire dans : " + expirationTimeInSeconds + " secondes");
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("Expiration du token (en date) : " + new java.util.Date(currentTimeMillis + expirationTimeInSeconds * 1000));

        // Retourner le token JWT
        return accessToken.toJwt(); // Retourne le token sous forme de chaîne
    }
}
