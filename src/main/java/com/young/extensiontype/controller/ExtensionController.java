package com.young.extensiontype.controller;

import com.young.extensiontype.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RequiredArgsConstructor
@Controller
public class ExtensionController {
    private final ExtensionService extensionService;
    private final SimpMessagingTemplate template;
    @GetMapping("/")
    public String extensionPage(Model model){
        model.addAttribute("dto",extensionService.getCurrentExtension());
        model.addAttribute("list",extensionService.getInitialExtension());
        return "ext/extension";
    }

    @PostMapping("/extension/add")
    public ResponseEntity<Boolean> addExtension(String ext){
        extensionService.addExtension(ext);
        sendMessage();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @DeleteMapping("/extension/delete")
    public ResponseEntity<Boolean> deleteExtension(String ext){
        extensionService.deleteExtension(ext);
        sendMessage();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @MessageMapping("/status.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage() {
        template.convertAndSend("/topic/public", extensionService.getCurrentExtension());
    }
}
