package org.example.applicationdecommunication.Controllers;

import org.example.applicationdecommunication.Services.WhatsAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    /**
     * Endpoint pour envoyer un message WhatsApp via form submission
     *
     * @param to      Numéro de téléphone du destinataire
     * @param message Message à envoyer
     * @return Redirection vers une page de succès ou d'erreur
     */
    @PostMapping("/send")
    public String sendWhatsAppMessage(
            @RequestParam String to,
            @RequestParam String message) {
        if (to == null || message == null) {
            return "redirect:/error"; // Rediriger vers une page d'erreur
        }

        String messageId = whatsAppService.sendWhatsAppMessage(to, message);
        return "redirect:/success"; // Rediriger vers une page de succès
    }
}