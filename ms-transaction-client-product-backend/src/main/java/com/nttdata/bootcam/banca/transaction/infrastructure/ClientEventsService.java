package com.nttdata.bootcam.banca.transaction.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEventCompra;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;

/**
 * Componente para consumir la orden de compra publicado por el cliente
 * 
 * @author wrodrigr
 */
@Component
public class ClientEventsService {

	@Autowired
	private MensajeCompra mensajeCompra;

	@KafkaListener(topics = "order-request-topic", containerFactory = "kafkaListenerContainerFactory", groupId = "my-group")
	public void consumerOrder(Event<?> event) {
		if (event.getClass().isAssignableFrom(ClientCreatedEventCompra.class)) {
			ClientCreatedEventCompra clientCreatedEventCompra = (ClientCreatedEventCompra) event;
			mensajeCompra.procesarMensaje(clientCreatedEventCompra);

		}

	}

}
