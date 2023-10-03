package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.Auto;
import com.valeriygulin.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutoController {

    private AutoService autoService;

    @Autowired
    public void setAutoService(AutoService autoService) {
        this.autoService = autoService;
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Auto>> add(@PathVariable long id, @RequestBody Auto auto) {
        try {
            this.autoService.addAuto(id, auto);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Auto>>> get() {
        List<Auto> list = this.autoService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<List<Auto>>> get(@RequestParam String id) {
        List<Auto> autos = this.autoService.getByStudentId(Long.parseLong(id));
        return new ResponseEntity<>(new ResponseResult<>(null, autos), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Auto>> get(@PathVariable long id) {
        try {
            Auto auto = this.autoService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Auto>> delete(@PathVariable long id) {
        try {
            Auto auto = this.autoService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Auto>> update(@RequestBody Auto auto) {
        try {
            if (auto.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Auto update = this.autoService.update(auto);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}