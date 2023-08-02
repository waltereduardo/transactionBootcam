package com.nttdata.bootcam.banca.transaction.infrastructure;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.EventType;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.TransactionEnviaCuentaCliente;
import com.nttdata.bootcam.banca.transaction.dto.event.CuentaClienteEvent;

import jakarta.annotation.PostConstruct;

/**
 * Componente para publicar cuenta de los clientes solicitados por los productos
 * 
 * @author wrodrigr
 */
@Component
public class TransactionPublicService {

	@Autowired
	private KafkaTemplate<String, Event<?>> producer;

	@Value("${order-request-topic}")
	private String topicCustomerOrder;

	@PostConstruct
	public void init() {
		System.out.println("topicCustomerOrder: " + topicCustomerOrder);
	}

	public void publishAccountClient(CuentaClienteEvent client) {
		TransactionEnviaCuentaCliente selected = new TransactionEnviaCuentaCliente();
		selected.setData(client);
		selected.setId(UUID.randomUUID().toString());
		selected.setType(EventType.SELECTED);
		selected.setDate(new Date());

		this.producer.send(topicCustomerOrder, selected);
		System.out.println("ACCOUNT WAS PUBLICHED " + selected.getData());
	}
}
