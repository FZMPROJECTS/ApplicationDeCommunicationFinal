package org.example.applicationdecommunication.Services;

import org.springframework.stereotype.Service;

@Service
public class TwilioVoiceService {

    // Method to generate TwiML for a voice call
    public String generateTwiml(String receiverIdentity) {
        // Basic TwiML for demo call
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Response>" +
                "   <Dial>" +
                "       <Client>" + receiverIdentity + "</Client>" +
                "   </Dial>" +
                "</Response>";
    }
}
