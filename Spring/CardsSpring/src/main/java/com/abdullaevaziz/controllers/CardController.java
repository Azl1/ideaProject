package com.abdullaevaziz.controllers;

import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.Card;
import com.abdullaevaziz.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<ResponseResult<Card>> add(@RequestBody Card card, @PathVariable long categoryId) {
        try {
            this.cardService.add(categoryId, card);
            return new ResponseEntity<>(new ResponseResult<>(null, card), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Card>>> get() {
        List<Card> list = this.cardService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<List<Card>>> get(@PathVariable long id) {
        try {
            List<Card> list = this.cardService.getCategories(id);
            return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Card>> delete(@PathVariable long id) {
        try {
            Card card = this.cardService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, card), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Card>> update(@RequestBody Card card) {
        try {
            if (card.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Card old = this.cardService.update(card);
            return new ResponseEntity<>(new ResponseResult<>(null, old), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
