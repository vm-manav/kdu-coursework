package com.example.springjpa.services;

import com.example.springjpa.dao.TenantDao;
import com.example.springjpa.entities.Tenant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TenantService {
    private TenantDao tenantDao;
    @Autowired
    public TenantService(TenantDao tenantDao) {
        this.tenantDao=tenantDao;
    }

    public void insertIntoTenant(Tenant tenant) {
        tenantDao.save(tenant);
        log.info("tenant added successfully");
    }
}
