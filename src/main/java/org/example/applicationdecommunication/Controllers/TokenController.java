package org.example.applicationdecommunication.Controllers;

import org.example.applicationdecommunication.Services.TwilioTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TwilioTokenService tokenService;

    // Endpoint to get the Twilio access token
    @GetMapping("/api/token")
    public String getToken(@RequestParam String identity) {
        return tokenService.generateToken(identity);
    }

    // Endpoint to generate TwiML for the voice call
    @GetMapping(value = "/api/voice/twiml", produces = "application/xml")
    public String handleVoiceCall(@RequestParam String identity, @RequestParam String to) {
        // Dynamic handling of receiver identity
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Response>" +
                "   <Dial>" +
                "       <Client>" + to + "</Client>" +
                "   </Dial>" +
                "</Response>";
    }
}
