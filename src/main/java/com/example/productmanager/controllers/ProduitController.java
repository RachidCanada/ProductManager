package com.example.productmanager.controllers;

import com.example.productmanager.models.Produit;
import com.example.productmanager.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public Optional<Produit> getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id);
    }

    @PostMapping
    public Produit addProduit(@RequestBody Produit produit) {
        return produitService.addProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produitDetails) {
        Optional<Produit> optionalProduit = produitService.getProduitById(id);
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setNom(produitDetails.getNom());
            produit.setPrix(produitDetails.getPrix());
            produit.setQuantite(produitDetails.getQuantite());

            Produit updatedProduit = produitService.updateProduit(produit);
            return ResponseEntity.ok(updatedProduit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }
}
