package com.nttdata.bootcam.banca.transaction.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;
import com.nttdata.bootcam.banca.transaction.service.CuentaClienteService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CuentaClienteProductoController {

	private CuentaClienteService cuentaClienteService;
	
	public CuentaClienteProductoController(CuentaClienteService cuentaClienteService) {
		this.cuentaClienteService=cuentaClienteService;
	}
	
	@GetMapping("/cuentaCliente/{idClient}/{idProduct}")
	public Mono<TransactionDAO> getCuentasCliente(@PathVariable String idClient, @PathVariable String idProduct){
		return cuentaClienteService.findCuentasClienteByIdClienteAndIdProducto(idClient, idProduct);
	}
}
