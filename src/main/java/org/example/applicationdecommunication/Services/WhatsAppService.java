package org.example.applicationdecommunication.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    @Value("${twilio.account.sid2}")
    private String accountSid;

    @Value("${twilio.auth.token2}")
    private String authToken;

    @Value("${twilio.whatsapp.from}")
    private String fromWhatsAppNumber;

    /**
     * Méthode pour envoyer un message WhatsApp
     *
     * @param toWhatsAppNumber Le numéro WhatsApp du destinataire (format : "whatsapp:+[codePays][numéro]")
     * @param messageBody      Le message à envoyer
     * @return L'ID du message envoyé
     */
    public String sendWhatsAppMessage(String toWhatsAppNumber, String messageBody) {
        // Initialiser Twilio avec les credentials
        Twilio.init(accountSid, authToken);

        // Envoyer le message
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + toWhatsAppNumber), // Destinataire
                new PhoneNumber("whatsapp:" + fromWhatsAppNumber), // Expéditeur (Twilio WhatsApp Number)
                messageBody // Corps du message
        ).create();

        // Retourner l'ID du message
        return message.getSid();
    }
}
