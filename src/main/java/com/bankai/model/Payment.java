package com.bankai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long id;
    private String accountNumber;
    private String accnHolderName;
    private String trnID;
    private Double amount;
    private String debitAccount;
    private String paymentStatus;
    private String paymentDate;

}