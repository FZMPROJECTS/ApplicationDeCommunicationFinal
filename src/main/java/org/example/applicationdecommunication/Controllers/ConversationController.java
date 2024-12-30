package org.example.applicationdecommunication.Controllers;

import org.example.applicationdecommunication.Services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    // Create a conversation
    @PostMapping
    public String createConversation(@RequestBody Map<String, String> request) {
        String friendlyName = request.get("friendlyName");
        return conversationService.createConversation(friendlyName);
    }

    // Send a message in a conversation
    @PostMapping("/{conversationSid}/messages")
    public String sendMessage(@PathVariable String conversationSid, @RequestBody Map<String, String> request) {
        String identity = request.get("identity");
        String body = request.get("body");
        return conversationService.sendMessage(conversationSid, identity, body);
    }

    // Get messages from a conversation
    @GetMapping("/{conversationSid}/messages")
    public List<Map<String, String>> getMessages(@PathVariable String conversationSid) {
        return conversationService.getMessages(conversationSid);
    }

    // Add a participant to a conversation
    @PostMapping("/{conversationSid}/participants")
    public String addParticipant(@PathVariable String conversationSid, @RequestBody Map<String, String> request) {
        String identity = request.get("identity");
        return conversationService.addParticipant(conversationSid, identity);
    }
}
