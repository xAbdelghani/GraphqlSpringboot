package com.example.tgraphql.repositry;

import com.example.tgraphql.entities.Compte;
import com.example.tgraphql.entities.Transaction;
import com.example.tgraphql.entities.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t JOIN FETCH t.compte WHERE t.compte = :compte")
    List<Transaction> findByCompte(Compte compte);
    @Query("SELECT SUM(t.montant) FROM Transaction t WHERE t.type = :type")
    double sumByType(TypeTransaction type);
}
