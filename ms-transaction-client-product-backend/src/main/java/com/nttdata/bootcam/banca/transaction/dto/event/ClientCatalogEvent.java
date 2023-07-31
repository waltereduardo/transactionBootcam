package com.nttdata.bootcam.banca.transaction.dto.event;


import lombok.Data;
/**
 * Objecto para serializacion
 * en la solicitud del catalogo
 * @author wrodrigr
 */
@Data
public class ClientCatalogEvent {
	private String typeDocument;
	private String numberDocument;
	private String typeClient;
	private String nameAll;

}
