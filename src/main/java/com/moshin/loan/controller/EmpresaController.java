package com.moshin.loan.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.moshin.loan.entity.table.Empresa;
import com.moshin.loan.service.empresa.EmpresaService;
import com.moshin.loan.service.error.GeneralResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    
    @Autowired
    EmpresaService empresaService;
    @Autowired
    GeneralResponse generalResponse;

    @PostMapping
    public ResponseEntity<Object> saveEmpresa(@RequestBody @Valid Empresa empresa, BindingResult result){
        return result.hasErrors()? generalResponse.response(result): ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresa));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getEmpresa(){
        List<Empresa> listEmp = empresaService.getEmpresa();
        return listEmp.isEmpty()? ResponseEntity.notFound().build(): ResponseEntity.ok(listEmp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Empresa>> getEmpresaByRuc(@PathVariable("id") String cRuc){
        Optional<Empresa> resEmp = empresaService.getEmpresaByRuc(cRuc);
        return !resEmp.isPresent()? ResponseEntity.notFound().build() :ResponseEntity.ok(resEmp);
    }
}
