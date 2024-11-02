package com.home.trupper.rest.controller;

import com.home.trupper.rest.exception.TrupperPracticeException;
import com.home.trupper.rest.persistence.repository.entities.SucursalEntity;
import com.home.trupper.rest.request.ProductRequest;
import com.home.trupper.rest.request.SucursalRequest;
import com.home.trupper.rest.service.DaoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/sucursal")
@Data
public class BranchController {

    private  final DaoService daoService;

    @PostMapping
    public void sucursal (@RequestBody SucursalRequest request) throws TrupperPracticeException {
        log.info("sucursalRequest: {}",request.toString());
        daoService.saveSucursal(request);
    }

    @GetMapping
    public SucursalEntity getBranchByName (@RequestParam (name="sucursal_name", required = true) String sucursal) throws TrupperPracticeException {
        SucursalEntity sucursalEntity = daoService.getSucursalData(sucursal);
        if (sucursalEntity == null) {
            throw new TrupperPracticeException("SUC-010","No se encontro la Sucursal solictada");
        }
        return sucursalEntity;
    }

    @PutMapping("product")
    public void updateProduct (@RequestBody ProductRequest request) throws TrupperPracticeException {
        log.info("request product: {}", request);
        daoService.updateProduct(request);
    }
}