package com.nttdata.bootcam.banca.transaction.infrastructure;

import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEventCompra;
import com.nttdata.bootcam.banca.transaction.util.Constantes;

/**
 * Servicio que verifica si existe un mensaje de orden de compra
 * @author wrodrigr
 */
@Service
public class VerificaOrdenCompraService {

	public boolean verifyMsgOrdenCompra(ClientCreatedEventCompra clientCreatedEventCompra) {

		if (Constantes.MSG_COMPRA.equals(clientCreatedEventCompra.getData().getMensaje())) {
			return true;
		}
		return false;
	}

}
