package com.nttdata.bootcam.banca.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionPost {
	
	@JsonProperty("identificador")
	private String idClient;
	private String idProduct;
	private String typeTransaction;
	private String dateTransaction;
	private String amountTransaction;
	private String currencyTransaction;
	private String descriptionTransaction;
}
