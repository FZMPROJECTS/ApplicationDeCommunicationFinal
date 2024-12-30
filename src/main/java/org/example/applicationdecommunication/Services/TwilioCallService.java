/*
package org.example.applicationdecommunication.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class TwilioCallService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.voice.sid}")
    private String voiceSid;

    private boolean isTwilioInitialized = false;

    private void initializeTwilio() {
        if (!isTwilioInitialized) {
            Twilio.init(accountSid, authToken);
            isTwilioInitialized = true;
        }
    }

    public String createCall(String fromClientIdentity, String toClientIdentity) {
        initializeTwilio(); // Ensure Twilio is initialized

        try {
            // Create a web-based call between two client identities
            Call call = Call.creator(
                    new com.twilio.type.Client(toClientIdentity),  // Receiver's client identity
                    new com.twilio.type.Client(fromClientIdentity), // Sender's client identity
                    URI.create("http://demo.twilio.com/docs/voice.xml") // TwiML URL
            ).create();

            return "Call SID: " + call.getSid();
        } catch (Exception e) {
            return "Error creating call: " + e.getMessage();
        }
    }
}
*/