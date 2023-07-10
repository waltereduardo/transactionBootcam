package com.nttdata.bootcam.banca.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;

public interface TransactionRepository extends ReactiveMongoRepository<TransactionDAO, String>{

}
