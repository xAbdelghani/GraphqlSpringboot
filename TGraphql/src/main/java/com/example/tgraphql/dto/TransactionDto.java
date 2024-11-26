package com.example.tgraphql.dto;

import com.example.tgraphql.entities.TypeTransaction;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Long compteId;
    private double montant;
    private Date date;
    private TypeTransaction type;
}
