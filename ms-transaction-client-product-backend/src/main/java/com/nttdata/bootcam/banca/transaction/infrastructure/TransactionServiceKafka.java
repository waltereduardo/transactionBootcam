package com.nttdata.bootcam.banca.transaction.infrastructure;

import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEventCompra;
import com.nttdata.bootcam.banca.transaction.dto.event.BuyProductEvent;
import com.nttdata.bootcam.banca.transaction.dto.event.CuentaClienteEvent;
import com.nttdata.bootcam.banca.transaction.util.Constantes;

/**
 * Servicio de transacciones. Para las uentas de los clientes
 * 
 * @author wrodrigr
 */
@Service
public class TransactionServiceKafka {

	private final TransactionPublicService transactionPublicService;

//	private final ClientEventsService clientEventsService;

	private final MensajeCompra mensajeCompra;

	public TransactionServiceKafka(TransactionPublicService transactionPublicService, MensajeCompra mensajeCompra) {
		super();
		this.transactionPublicService = transactionPublicService;
		this.mensajeCompra = mensajeCompra;
	}

	public CuentaClienteEvent saveAccountClient(CuentaClienteEvent cuentaClienteEvent) {
		System.out.println("El producto solicita las cuentas" + cuentaClienteEvent);
		if (Constantes.MSG_CUENTA_CLIENTE.equals(cuentaClienteEvent.getMensaje())) {
//			ClientCreatedEventCompra verifyMsgCompra = new ClientCreatedEventCompra();
//			BuyProductEvent bpe = new BuyProductEvent();
//			bpe.setMensaje(Constantes.MSG_COMPRA);
//			verifyMsgCompra.setData(bpe);
//
//			clientEventsService.consumerOrder(verifyMsgCompra);
//			if (clientEventsService.verifyMsgOrdenCompra()) {
			ClientCreatedEventCompra clientCreatedEventCompra = new ClientCreatedEventCompra();
			BuyProductEvent bpe = new BuyProductEvent();
			bpe.setMensaje(Constantes.MSG_COMPRA);
			bpe.setIdClient(cuentaClienteEvent.getIdClient());
			bpe.setIdProduct(cuentaClienteEvent.getIdProduct());
			clientCreatedEventCompra.setData(bpe);
//			mensajeCompra.procesarMensaje(clientCreatedEventCompra);
			if(mensajeCompra.procesarMensaje(clientCreatedEventCompra))
					this.transactionPublicService.publishAccountClient(cuentaClienteEvent);
//			}else {
//				System.out.println("No hay ORDEN DE COMPRA");
//				CuentaClienteEvent cuentaClienteEventE = new CuentaClienteEvent();
//				cuentaClienteEventE.setMensaje("Sin datos. NO HAY ORDEN DE COMPRA");
//				cuentaClienteEvent = cuentaClienteEventE;
//			}

		} else {
			System.out.println("Mensaje incorrecto");
			CuentaClienteEvent cuentaClienteEventE = new CuentaClienteEvent();
			cuentaClienteEventE.setMensaje("Sin datos. Mensaje incorrecto");
			cuentaClienteEvent = cuentaClienteEventE;
		}
		return cuentaClienteEvent;
	}

}
