package com.nttdata.bootcam.banca.consulta.client.infraestructure.event;

import com.nttdata.bootcam.banca.transaction.dto.event.CuentaClienteEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionEnviaCuentaCliente extends Event<CuentaClienteEvent> {

}
