package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.dto.PurchaseDTO;
import com.example_pgr.demo_pgr.model.Purchase;
import com.example_pgr.demo_pgr.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // OBTENER TODAS LAS COMPRAS
    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.listPurchases();
        if (purchases.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PurchaseDTO> purchaseDTOS = new ArrayList<>();

        for (Purchase purchase : purchases) {
            PurchaseDTO dto = new PurchaseDTO();
            dto.setId_buy(purchase.getId_buy());
            dto.setDate(purchase.getDate());
            dto.setTotal_price(purchase.getTotal_price());
            dto.setId_user(purchase.getUser().getId_user());
            dto.setClient_name(purchase.getUser().getName());

            purchaseDTOS.add(dto);
        }

        return ResponseEntity.ok(purchaseDTOS);
    }

    // OBTENER COMPRA POR LA ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> findById(@PathVariable int id) {
        Purchase purchase = purchaseService.findById(id);
        if (purchase == null) {
            return ResponseEntity.notFound().build();
        }

        PurchaseDTO dto = new PurchaseDTO();
        dto.setId_buy(purchase.getId_buy());
        dto.setDate(purchase.getDate());
        dto.setTotal_price(purchase.getTotal_price());
        dto.setClient_name(purchase.getUser().getName());
        dto.setId_user(purchase.getUser().getId_user());

        return ResponseEntity.ok(dto);
    }

    // CREAR UNA NUEVA COMPRA
    @PostMapping
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        try {
            Purchase saved = purchaseService.savePurchase(purchase);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ACTUALIZAR UNA COMPRA
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable int id, @RequestBody Purchase purchase) {
        Purchase current = purchaseService.findById(id);
        if (current == null) {
            return ResponseEntity.notFound().build();
        }

        current.setId_buy(purchase.getId_buy());
        current.setDate(purchase.getDate());
        current.setTotal_price(purchase.getTotal_price());
        current.setUser(purchase.getUser());

        purchaseService.savePurchase(current);
        return ResponseEntity.ok(current);
    }

    // ELIMINAR COMPRA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable int id) {
        try {
            purchaseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}