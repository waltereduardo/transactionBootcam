package com.nttdata.bootcam.banca.transaction.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEventCompra;
import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;
import com.nttdata.bootcam.banca.transaction.service.CuentaClienteService;
import com.nttdata.bootcam.banca.transaction.util.Constantes;

import reactor.core.publisher.Mono;

/**
 * Clase que verifica la existencia de un mensaje de compra, en el topico
 * order-request-topic
 * 
 * @author wrodrigr
 */
@Component
public class MensajeCompra {

	@Autowired
	private CuentaClienteService cuentaClienteService;

	public boolean procesarMensaje(ClientCreatedEventCompra clientCreatedEventCompra) {
		System.out.println("TRANSACTION TIPO DE ORDEN::" + clientCreatedEventCompra.getData().getTypeClient() + "////"
				+ clientCreatedEventCompra.getData().getCantidad() + "/////"
				+ clientCreatedEventCompra.getData().getTypeProduct()
				+clientCreatedEventCompra.getId());
		boolean verificaMensaje=false;
		if (Constantes.MSG_COMPRA.equals(clientCreatedEventCompra.getData().getMensaje())) {
			System.out.println("HAY ORDEN DE COMPRA----------------");
			verificaMensaje=true;
			// 1. Realizar las validaciones. Establecer contrato con transacciones
			Mono<TransactionDAO> dx = cuentaClienteService.findCuentasClienteByIdClienteAndIdProducto(
					clientCreatedEventCompra.getData().getIdClient(),
					clientCreatedEventCompra.getData().getIdProduct());
			dx.switchIfEmpty(Mono.empty()).doOnNext(on -> {
				System.out.println("SERVICIO LLENO" + "//" + on);
			}).subscribe(px -> {
				System.out.println("SUSCRIBE LLENO" + "//" + px);
			});

		} else {
			verificaMensaje=false;
			System.out.println("NO HAY NADA----------------");
		}
		return verificaMensaje;
	}
	
//	private boolean checkIfMessageExistsInTopic(String mensajeId) {
//	   
//	}
}
