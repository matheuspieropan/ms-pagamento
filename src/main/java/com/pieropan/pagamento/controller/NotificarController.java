package com.pieropan.pagamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificar")
public class NotificarController {

    @PostMapping
    public ResponseEntity notificar(@RequestBody Object obj) {
        System.out.println(obj.toString());
        return ResponseEntity.ok().build();
    }
}