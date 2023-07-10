package com.nttdata.bootcam.banca.transaction.repository.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Data
@Document("transactionclientproduct")
public class TransactionDAO {
	
	@Id
	private String id;
	private String idClient;
	private String idProduct;
	private String typeTransaction;
	private String dateTransaction;
	private String amountTransaction;
	private String currencyTransaction;
	private String descriptionTransaction;
}
