package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Purchase;
import com.example_pgr.demo_pgr.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> listPurchases() {
        return purchaseRepository.findAll();
    }
    public Purchase findById(Integer id) {
        return purchaseRepository.findById(id).orElse(null);
    }
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
    public boolean deleteById(Integer id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
