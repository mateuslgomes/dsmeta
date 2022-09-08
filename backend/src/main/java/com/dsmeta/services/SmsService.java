package com.dsmeta.services;

import com.dsmeta.entities.Sale;
import com.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SmsService {

    private final SaleRepository repository;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public ResponseEntity<Object> sendSms(Long id) {
        Optional<Sale> saleOptional = repository.findById(id);
        if (saleOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Sale sale = saleOptional.get();

        String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
        String body = "O vendedor " + sale.getSellerName() + " foi destaque em " + date + " com um total de R$" +
        String.format("%.2f", sale.getAmount()) + ".";

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, body).create();
        return ResponseEntity.ok().build();
    }
}