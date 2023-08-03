package com.nttdata.bootcam.banca.transaction.infrastructure;

import java.util.Date;

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
		System.out.println("TRANSACTION TIPO DE ORDEN--->::" + clientCreatedEventCompra.getData().getTypeClient()
				+ "////" + clientCreatedEventCompra.getData().getCantidad() + "/////"
				+ clientCreatedEventCompra.getData().getTypeProduct() + clientCreatedEventCompra.getId());
		boolean verificaMensaje = false;
		if (Constantes.MSG_COMPRA.equals(clientCreatedEventCompra.getData().getMensaje())) {
			System.out.println("HAY ORDEN DE COMPRA-------CON ID ---------" + clientCreatedEventCompra.getId());
			verificaMensaje = true;

			// 1. Verificar el tipo de cliente
			Mono<TransactionDAO> monoT = cuentaClienteService.findCuentasClienteByIdClienteAndIdProducto(
					clientCreatedEventCompra.getData().getIdClient(),
					clientCreatedEventCompra.getData().getIdProduct());

			Predicate<TransactionDAO> ptrcuentaAhorro = ptr -> ptr.getTypeCount().equals("1");
			Predicate<TransactionDAO> ptrcuentaCorriente = ptr -> ptr.getTypeCount().equals("2");
			Predicate<TransactionDAO> ptrcuentaPlazoFijo = ptr -> ptr.getTypeCount().equals("3");
			monoT.hasElement().subscribe(prod -> {
				if (prod) {
					System.out.println("En la subcripcion :: " + prod);
					monoT.doOnNext(res -> {
						System.out.println(" EL TIPO DE CLIENTE-->" + res.getTypeClient());
						monoT.filter(ptrcuentaAhorro).doOnNext(con -> {
							System.out.println("EL TIPO DE CUENTA ES :" + "CUENTA DE AHORRO");
						}).subscribe();
						monoT.filter(ptrcuentaCorriente).doOnNext(con -> {
							System.out.println("EL TIPO DE CUENTA ES :" + "CUENTA CORRIENTE");
						}).subscribe();
						monoT.filter(ptrcuentaPlazoFijo).doOnNext(con -> {
							System.out.println("EL TIPO DE CUENTA ES :" + "CUENTA A PLAZO FIJO");
						}).subscribe();
					}).subscribe();
				} else {
					System.out.println("El mono esta vacio :: " + prod);
				}

			}, error -> {
				System.out.println(" hay un error" + error.getMessage());
			});

//			// 1. Realizar las validaciones. Establecer contrato con transacciones
//			Mono<TransactionDAO> dx = cuentaClienteService.findCuentasClienteByIdClienteAndIdProducto(
//					clientCreatedEventCompra.getData().getIdClient(),
//					clientCreatedEventCompra.getData().getIdProduct());
//			dx.switchIfEmpty(Mono.empty()).doOnNext(on -> {
//				System.out.println("SERVICIO LLENO" + "//" + on);
//			}).subscribe(px -> {
//				System.out.println("SUSCRIBE LLENO" + "//" + px);
//			});

//			// 1.Registrar en la cuenta del cliente
//			TransactionDAO tdao = new TransactionDAO();
//			tdao.setId(clientCreatedEventCompra.getId());
//			tdao.setIdClient(clientCreatedEventCompra.getData().getIdClient());
//			tdao.setIdProduct(clientCreatedEventCompra.getData().getIdProduct());
//			tdao.setTypeTransaction("DEPOSITO");
//			tdao.setDateTransaction(new Date());
//			tdao.setAmountTransaction(String.valueOf(clientCreatedEventCompra.getData().getCantidad()));
//			tdao.setCurrencyTransaction("SOLES");
//			tdao.setDescriptionTransaction(
//					"Deposito del cliente " + tdao.getIdClient() + "Por el producto" + tdao.getIdProduct());
//			cuentaClienteService.registraCompraCliente(tdao).subscribe();

		} else {
			verificaMensaje = false;
			System.out.println("NO HAY NADA----------------");
		}
		return verificaMensaje;
	}

}
