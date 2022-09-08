package com.dsmeta.controllers;

import com.dsmeta.entities.Sale;
import com.dsmeta.repositories.SaleRepository;
import com.dsmeta.services.SaleService;
import com.dsmeta.services.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    private final SaleRepository repository;
    private final SmsService smsService;
    private final SaleService service;

    @GetMapping
    public ResponseEntity<Page<Sale>> findBySales(
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
            Pageable pageable) {
        return service.findSales(minDate, maxDate, pageable);
    }

    @GetMapping("/{id}/notification")
    public ResponseEntity<Object> notifySms(@PathVariable Long id) {
        return smsService.sendSms(id);
    }

}
