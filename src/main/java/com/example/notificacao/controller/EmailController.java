package com.example.notificacao.controller;


import com.example.notificacao.business.EmailService;
import com.example.notificacao.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefaDTO dto){
        emailService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }
}
