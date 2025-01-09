package org.example.applicationdecommunication.Services;

import com.twilio.Twilio;
import com.twilio.rest.conversations.v1.Conversation;
import com.twilio.rest.conversations.v1.conversation.Message;
import com.twilio.rest.conversations.v1.conversation.Participant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ConversationService {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public String createConversation(String friendlyName) {
        Conversation conversation = Conversation.creator()
                .setFriendlyName(friendlyName)
                .create();
        return conversation.getSid();
    }

    public String sendMessage(String conversationSid, String identity, String body) {
        Message message = Message.creator(conversationSid)
                .setAuthor(identity)
                .setBody(body)
                .create();
        return message.getSid();
    }

    public List<Map<String, String>> getMessages(String conversationSid) {
        List<Map<String, String>> messagesList = new ArrayList<>();
        for (Message message : Message.reader(conversationSid).read()) {
            messagesList.add(Map.of(
                    "author", message.getAuthor(),
                    "body", message.getBody(),
                    "timestamp", message.getDateCreated().toString()
            ));
        }
        return messagesList;
    }

    public String addParticipant(String conversationSid, String identity) {
        Participant participant = Participant.creator(conversationSid)
                .setIdentity(identity)
                .create();
        return participant.getSid();
    }
}
