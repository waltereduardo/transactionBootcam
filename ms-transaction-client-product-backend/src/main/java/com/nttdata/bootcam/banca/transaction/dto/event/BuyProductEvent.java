package com.nttdata.bootcam.banca.transaction.dto.event;



import lombok.Data;
/**
 * Objeto para la serializacion
 * en la solicitud de compra
 * @author wrodrigr
 */
@Data
public class BuyProductEvent {
	private String typeClient;
	private String typeProduct;
	private String idClient;
	private String idProduct;
	private int cantidad;
	private String mensaje;
}
