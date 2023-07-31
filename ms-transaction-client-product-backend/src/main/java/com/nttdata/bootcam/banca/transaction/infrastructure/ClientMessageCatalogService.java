package com.nttdata.bootcam.banca.transaction.infrastructure;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nttdata.bootcam.banca.transaction.dto.event.ClientCatalogEvent;

/**
 * Servicio que verifica la existencia de mensajes, en el topico de catalogos, a
 * partir de su identificador unico.
 * 
 * @author wrodrigr
 */
@Service
public class ClientMessageCatalogService {


}
