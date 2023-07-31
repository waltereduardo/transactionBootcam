package com.nttdata.bootcam.banca.transaction.dto.event;

import lombok.Data;
/**
 * Objeto para la serializacion
 * en el envio de la cuenta del cliente
 * @author wrodrigr
 */
@Data
public class CuentaClienteEvent {
	private String id;
	private String idClient;
	private String idProduct;
	private String typeTransaction;
	private String dateTransaction;
	private String amountTransaction;
	private String currencyTransaction;
	private String descriptionTransaction;
	
	private String mensaje;
}
