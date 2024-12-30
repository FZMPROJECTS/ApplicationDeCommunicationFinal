package org.example.applicationdecommunication.Services;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VoiceGrant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioTokenService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.api.key}")
    private String apiKey;

    @Value("${twilio.api.secret}")
    private String apiSecret;

    @Value("${twilio.voice.sid}")
    private String voiceSid;

    public String generateToken(String identity) {
        VoiceGrant voiceGrant = new VoiceGrant();
        voiceGrant.setOutgoingApplicationSid(voiceSid);  // Ensure this SID is correct and matches what you've set up in Twilio Console

        AccessToken token = new AccessToken.Builder(accountSid, apiKey, apiSecret)
                .identity(identity)
                .grant(voiceGrant)
                .build();

        return token.toJwt();
    }
}
