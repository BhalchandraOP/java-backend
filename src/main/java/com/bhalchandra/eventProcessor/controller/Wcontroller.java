package com.bhalchandra.eventProcessor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bhalchandra.eventProcessor.dto.TransactionRequest;
import com.bhalchandra.eventProcessor.entity.WTransaction;
import com.bhalchandra.eventProcessor.service.WService;

@RestController
@RequestMapping("/api/v1/transactions")
public class Wcontroller {

    private final WService service;

    public Wcontroller(WService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public ResponseEntity<WTransaction> process(
            @RequestBody TransactionRequest request) {

        WTransaction transaction = service.process(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transaction);
    }
}