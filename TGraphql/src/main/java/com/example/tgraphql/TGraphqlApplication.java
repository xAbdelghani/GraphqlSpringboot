package com.example.tgraphql;

import com.example.tgraphql.entities.Compte;
import com.example.tgraphql.entities.Transaction;
import com.example.tgraphql.entities.TypeCompte;
import com.example.tgraphql.entities.TypeTransaction;
import com.example.tgraphql.repositry.CompteRepository;
import com.example.tgraphql.repositry.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TGraphqlApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CompteRepository compteRepository , TransactionRepository transactionRepository) {
        return args -> {

            Compte compte1 =compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
            Compte compte2 =compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
            Compte compte3 =compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));

            compteRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });

            transactionRepository.save(new Transaction(null, 1500.0, new Date(), TypeTransaction.DEPOT, compte1));
            transactionRepository.save(new Transaction(null, 500.0, new Date(), TypeTransaction.RETRAIT, compte1));


            transactionRepository.save(new Transaction(null, 2000.0, new Date(), TypeTransaction.DEPOT, compte2));
            transactionRepository.save(new Transaction(null, 1000.0, new Date(), TypeTransaction.RETRAIT, compte2));


            transactionRepository.save(new Transaction(null, 3000.0, new Date(), TypeTransaction.DEPOT, compte3));
            transactionRepository.save(new Transaction(null, 700.0, new Date(), TypeTransaction.RETRAIT, compte3));


            transactionRepository.findAll().forEach(t -> {
                System.out.println(t.toString());
            });

        };
    }

}
