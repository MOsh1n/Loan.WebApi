package com.moshin.loan.controller;

import java.util.List;

import com.moshin.loan.entity.table.TipoPrestamo;
import com.moshin.loan.service.tipo_prestamo.TipoPrestamoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tipoPrestamo")
public class TipoPrestamoController {
    
    @Autowired
    TipoPrestamoService tipoPrestamoService;

    @PostMapping
    public ResponseEntity<TipoPrestamo> saveTipoPrestamo(@RequestBody TipoPrestamo tipoPrestamo){
        TipoPrestamo resTipo = tipoPrestamoService.save(tipoPrestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(resTipo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TipoPrestamo>> getTipoPrestamoByRuc(@PathVariable("id") String cRuc){
        List<TipoPrestamo> listTipo = tipoPrestamoService.getTipoByRuc(cRuc);
        return listTipo.isEmpty()? ResponseEntity.notFound().build(): ResponseEntity.ok(listTipo);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> logicalDelete(@RequestParam Long id){
        TipoPrestamo resTipo =  tipoPrestamoService.logicalDelete(id);
        return resTipo.getCEstado().equals("0")? ResponseEntity.ok(true): ResponseEntity.notFound().build();
    }
}
