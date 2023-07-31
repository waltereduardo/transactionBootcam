package com.nttdata.bootcam.banca.transaction.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEventCompra;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;
import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;
import com.nttdata.bootcam.banca.transaction.service.CuentaClienteService;
import com.nttdata.bootcam.banca.transaction.util.Constantes;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Componente para consumir la orden de compra publicado por el cliente
 * 
 * @author wrodrigr
 */
@Component
public class ClientEventsService {

	@Autowired
//	private CuentaClienteService cuentaClienteService;
	private MensajeCompra mensajeCompra;

	@KafkaListener(topics = "order-request-topic", containerFactory = "kafkaListenerContainerFactory", groupId = "my-group")
	public void consumerOrder(Event<?> event) { 
		if (event.getClass().isAssignableFrom(ClientCreatedEventCompra.class)) {
			ClientCreatedEventCompra clientCreatedEventCompra = (ClientCreatedEventCompra) event;
			mensajeCompra.procesarMensaje(clientCreatedEventCompra);
//			System.out.println("TRANSACTION TIPO DE ORDEN::" + clientCreatedEventCompra.getData().getTypeClient()
//					+ "////" + clientCreatedEventCompra.getData().getCantidad() + "/////"
//					+ clientCreatedEventCompra.getData().getTypeProduct());
//
//			if (Constantes.MSG_COMPRA.equals(clientCreatedEventCompra.getData().getMensaje())) {
//				System.out.println("HAY ORDEN DE COMPRA----------------");
//			
//				// 1. Realizar las validaciones. Establecer contrato con transacciones
//				Mono<TransactionDAO> dx = cuentaClienteService.findCuentasClienteByIdClienteAndIdProducto(
//						clientCreatedEventCompra.getData().getIdClient(),
//						clientCreatedEventCompra.getData().getIdProduct());
//				dx.switchIfEmpty(Mono.empty()).doOnNext(on -> {
//					System.out.println("SERVICIO LLENO" + "//" + on);
//				}).subscribe(px -> {
//					System.out.println("SUSCRIBE LLENO" + "//" + px);
//				});
//
//			} else {
//				System.out.println("NO HAY NADA----------------");
//			}
		}

	}

	

}
