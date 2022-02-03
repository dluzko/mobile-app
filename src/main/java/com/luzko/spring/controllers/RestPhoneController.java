package com.luzko.spring.controllers;

import com.luzko.spring.models.Smartphone;
import com.luzko.spring.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest-phones")
public class RestPhoneController {
    private final SmartphoneService smartphoneService;

    @Autowired
    public RestPhoneController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @GetMapping()
    public ResponseEntity<List<Smartphone>> phones() {
        final List<Smartphone> phones = smartphoneService.index();
        return phones !=null && !phones.isEmpty() ?
                new ResponseEntity<>(phones, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Smartphone> show(@PathVariable("id") int id) {
        final Smartphone smartphone = smartphoneService.show(id);
        return smartphone != null ?
                new ResponseEntity<>(smartphone, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody @Valid Smartphone smartphone, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        smartphoneService.save(smartphone);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Smartphone smartphone, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (smartphoneService.update(id, smartphone) == false) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (smartphoneService.delete(id) == false) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
