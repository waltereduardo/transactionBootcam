package com.nttdata.bootcam.banca.transaction.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcam.banca.transaction.dto.Transaction;
import com.nttdata.bootcam.banca.transaction.dto.TransactionPost;
import com.nttdata.bootcam.banca.transaction.repository.TransactionRepository;
import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movimiento")
public class TransactionResource {

	@Autowired
	private TransactionRepository transactionRepository;

	@GetMapping
	public Flux getAllChange(Transaction transaction) {
		return transactionRepository.findAll().map(this::fromTransactionToTransactionResponse);
	}

	@GetMapping("/{id}")
	public Mono<Transaction> findTransactionById(@PathVariable String id) {
		return transactionRepository.findById(id).map(this::fromTransactionDaoToTransaction);
	}

	@PostMapping
	public Mono<Transaction> createTransaction(@RequestBody TransactionPost transactionPost) {
		return transactionRepository.save(this.fromTransactionPostToTransactionDao(transactionPost))
				.map(this::fromTransactionDaoToTransaction);
	}

	private TransactionPost fromTransactionToTransactionResponse(TransactionDAO transaction) {
		TransactionPost trp = new TransactionPost();
		trp.setIdClient(transaction.getIdClient());
		trp.setIdProduct(transaction.getIdProduct());
		trp.setTypeTransaction(transaction.getTypeTransaction());
		trp.setAmountTransaction(transaction.getAmountTransaction());
		trp.setCurrencyTransaction(transaction.getCurrencyTransaction());
		trp.setDateTransaction(transaction.getDateTransaction());
		trp.setDescriptionTransaction(transaction.getDescriptionTransaction());
		return trp;
	}

	private Transaction fromTransactionDaoToTransaction(TransactionDAO transaction) {
		Transaction tr = new Transaction();
		tr.setIdClient(transaction.getIdClient());
		tr.setIdProduct(transaction.getIdProduct());
		tr.setTypeTransaction(transaction.getTypeTransaction());
		tr.setAmountTransaction(transaction.getAmountTransaction());
		tr.setCurrencyTransaction(transaction.getCurrencyTransaction());
		tr.setDateTransaction(transaction.getDateTransaction());
		tr.setDescriptionTransaction(transaction.getDescriptionTransaction());
		return tr;
	}

	private TransactionDAO fromTransactionPostToTransactionDao(TransactionPost transactionPost) {
		TransactionDAO tr = new TransactionDAO();
		tr.setIdClient(transactionPost.getIdClient());
		tr.setIdProduct(transactionPost.getIdProduct());
		tr.setTypeTransaction(transactionPost.getTypeTransaction());
		tr.setAmountTransaction(transactionPost.getAmountTransaction());
		tr.setCurrencyTransaction(transactionPost.getCurrencyTransaction());
		tr.setDateTransaction(transactionPost.getDateTransaction());
		tr.setDescriptionTransaction(transactionPost.getDescriptionTransaction());
		return tr;
	}

}
