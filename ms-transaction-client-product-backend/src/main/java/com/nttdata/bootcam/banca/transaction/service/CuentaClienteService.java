package com.nttdata.bootcam.banca.transaction.service;

import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.transaction.contrato.CuentaClienteProductoRepository;
import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;

import reactor.core.publisher.Mono;

@Service
public class CuentaClienteService {

	private final CuentaClienteProductoRepository cuentaClienteProductoRepository;

	public CuentaClienteService(CuentaClienteProductoRepository cuentaClienteProductoRepository) {
		this.cuentaClienteProductoRepository = cuentaClienteProductoRepository;
	}

	public Mono<TransactionDAO> findCuentasClienteByIdClienteAndIdProducto(String idCliente, String idProducto) {
		return cuentaClienteProductoRepository.findByIdClientAndIdProduct(idCliente, idProducto);
	};

	public Mono<TransactionDAO> registraCompraCliente(TransactionDAO transactionDAO) {
		System.out.println("Mono<TransactionDAO> GUARDANDO::" + transactionDAO);
		return cuentaClienteProductoRepository.save(transactionDAO);
	};
}
