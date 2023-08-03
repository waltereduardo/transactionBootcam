package com.nttdata.bootcam.banca.transaction.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Transaction {

	@JsonProperty("identificador")
	private String id;
	private String idClient;
	private String idProduct;
	private String typeTransaction;
	private String typeClient;
	private String typeCount;
	private Date dateTransaction;
	private String amountTransaction;
	private String currencyTransaction;
	private String descriptionTransaction;

}
