/*
package org.example.applicationdecommunication.Controllers;

import org.example.applicationdecommunication.Services.TwilioCallService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calls")
public class CallController {

    private final TwilioCallService twilioCallService;

    public CallController(TwilioCallService twilioCallService) {
        this.twilioCallService = twilioCallService;
    }

    @PostMapping("/start")
    public String startCall(@RequestParam String from, @RequestParam String to) {
        return twilioCallService.createCall(from, to);
    }
}
*/