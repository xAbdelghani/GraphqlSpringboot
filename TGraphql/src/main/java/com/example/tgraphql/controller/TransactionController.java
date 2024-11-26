package com.example.tgraphql.controller;

import com.example.tgraphql.dto.TransactionDto;
import com.example.tgraphql.entities.Compte;
import com.example.tgraphql.entities.Transaction;
import com.example.tgraphql.entities.TypeTransaction;
import com.example.tgraphql.repositry.CompteRepository;
import com.example.tgraphql.repositry.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;




@Controller
@AllArgsConstructor
public class TransactionController{
    private TransactionRepository transactionRepository;
    private CompteRepository compteRepository;

    @MutationMapping
    public Transaction addTransaction(@Argument TransactionDto transactionDto) {

        Compte compte = compteRepository.findById(transactionDto.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));



        Transaction transaction = new Transaction();
        transaction.setMontant(transactionDto.getMontant());
        transaction.setDate(transactionDto.getDate());
        transaction.setType(transactionDto.getType());
        transaction.setCompte(compte);


        transactionRepository.save(transaction);

        return transaction;
    }


    @QueryMapping
    public List<Transaction> allTransactions() {

        return transactionRepository.findAll();
    }

    @QueryMapping
    public List<Transaction> compteTransactions(@Argument Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        return transactionRepository.findByCompte(compte);
    }

    @QueryMapping
    public Map<String, Object> transactionStats() {
        long count = transactionRepository.count();
        double sumDepots = transactionRepository.sumByType(TypeTransaction.DEPOT);
        double sumRetraits = transactionRepository.sumByType(TypeTransaction.RETRAIT);
        return Map.of(
                "count", count,
                "sumDepots", sumDepots,
                "sumRetraits", sumRetraits
        );
    }

}
