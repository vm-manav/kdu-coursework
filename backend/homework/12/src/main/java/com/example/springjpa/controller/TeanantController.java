package com.example.springjpa.controller;

import com.example.springjpa.entities.Tenant;
import com.example.springjpa.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeanantController {
    private TenantService tenantService;

    @Autowired
    public TeanantController(TenantService tenantService) {
        this.tenantService=tenantService;
    }

    @PostMapping("/add-tenant")
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant) {
        tenantService.insertIntoTenant(tenant);
        return ResponseEntity.ok("Tenant added successfully");
    }
}
