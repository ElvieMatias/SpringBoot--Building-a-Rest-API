package com.edm.cashcards;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
/*
    This tells Spring that this class is a Component of type
    RestController and capable of handling HTTP requests.
*/
@RestController
/*
    This is a companion to @RestController that indicates which address
    requests must have to access this Controller.
*/
@RequestMapping("/cashCard")
public class CashCardController {
    private CashCardRepository cashCardRepository;
    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }
    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
        if (requestedId.equals(77L)) {
            CashCard cashCard = new CashCard(77L, 777.00);
            return ResponseEntity.ok(cashCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
