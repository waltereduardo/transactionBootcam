package com.nttdata.bootcam.banca.transaction.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcam.banca.transaction.repository.TransactionRepository;
import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;

import org.springframework.http.MediaType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HandlerTransaction {
	@Autowired
	private TransactionRepository transactionRepository;

	public Mono<ServerResponse> getTransactionAll(ServerRequest request) {
		Flux<TransactionDAO> transactionStream = transactionRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(transactionStream, TransactionDAO.class);
	}

	public Mono<ServerResponse> findTransactionById(ServerRequest request) {
		int productId = Integer.valueOf(request.pathVariable("input"));
		Mono<TransactionDAO> transactionStream = transactionRepository.findById(String.valueOf(productId));
		return ServerResponse.ok().body(transactionStream, TransactionDAO.class);
	}

	public Mono<ServerResponse> saveTransaction(ServerRequest request) {
		Mono<TransactionDAO> transactionMono = request.bodyToMono(TransactionDAO.class);
		Mono<String> saveResponse = transactionMono.map(dto -> dto.getId() + ":" + dto.getIdClient()+":"+dto.getIdProduct()+":"+dto.getAmountTransaction()+":"+dto.getCurrencyTransaction()+":"+dto.getDescriptionTransaction()+":"+dto.getDateTransaction());
		return ServerResponse.ok().body(saveResponse, String.class);
	}
}
