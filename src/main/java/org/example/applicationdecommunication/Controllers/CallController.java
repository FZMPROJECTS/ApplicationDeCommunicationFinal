package org.example.applicationdecommunication.Controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VoiceGrant;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallController {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.api.key}")
    private String apiKey;

    @Value("${twilio.api.secret}")
    private String apiSecret;

    @Value("${twilio.voice.sid}")
    private String voiceSid;

    @Value("${twilio.trial_number}")
    private String trialNumber;

    // Generate a Twilio Access Token
    @GetMapping("/token")
    public String getToken(@RequestParam String identity) {
        // Creating a VoiceGrant
        VoiceGrant voiceGrant = new VoiceGrant();
        voiceGrant.setOutgoingApplicationSid(voiceSid); // Set SID for outgoing calls

        // Create AccessToken with provided credentials
        AccessToken token = new AccessToken.Builder(
                accountSid,
                apiKey,
                apiSecret
        ).identity(identity)
                .grant(voiceGrant) // Grant the token permission for voice
                .build();

        return token.toJwt();
    }

    // Make a call to a phone number
    @PostMapping("/make-call")
    public String makeCall(@RequestParam String toPhoneNumber) {
        Twilio.init(accountSid, authToken); // Initialize Twilio with your SID and Auth Token

        // Create and initiate the call using the provided phone number
        Call call = Call.creator(
                new PhoneNumber(toPhoneNumber),  // Destination phone number
                new PhoneNumber(trialNumber),     // Twilio trial number (source)
                new com.twilio.type.Twiml("<Response><Say>Test call from your application!</Say></Response>") // Twilio XML response
        ).create();

        // Return the SID of the initiated call
        return "Call initiated with SID: " + call.getSid();
    }
}
