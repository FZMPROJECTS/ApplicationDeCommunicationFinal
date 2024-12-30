package org.example.applicationdecommunication.Services;

import com.twilio.Twilio;
import com.twilio.rest.conversations.v1.Conversation;
import com.twilio.rest.conversations.v1.conversation.Message;
import com.twilio.rest.conversations.v1.conversation.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ConversationService {

    private final String ACCOUNT_SID = "AC6b2e4698cc8a0feaa596c456f3eeb3dd";
    private final String AUTH_TOKEN = "6e3f8b073a1e554e18aa9c8da61dd65d";


    public ConversationService() {
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
