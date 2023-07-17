package com.nttdata.bootcam.banca.transaction.config;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.bootcam.banca.transaction.dto.TransactionPost;
import com.nttdata.bootcam.banca.transaction.handler.HandlerTransaction;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;


@Configuration
public class RouterConfig {

	@Autowired
	private HandlerTransaction handlerTransaction;

	@RouterOperations({ 
		@RouterOperation(path = "/router/movimiento", 
				produces = {
							MediaType.APPLICATION_JSON_VALUE }, 
							method = RequestMethod.GET, 
							beanClass = HandlerTransaction.class, 
							beanMethod = "getTransactionAll", 
							operation = @Operation(
									operationId = "getTransactionAll", 
									responses = {
												@ApiResponse(
														responseCode = "200", 
														description = "successful operation", 
														content = @Content(
																schema = @Schema(
																		implementation = TransactionPost.class)
																)
														) 
												}
									)
		),
        @RouterOperation(
                path = "/router/movimiento/{input}",
                produces = {
                        MediaType.APPLICATION_JSON_VALUE
                },
                method = RequestMethod.GET,
                beanClass = HandlerTransaction.class,
                beanMethod = "findTransaction",
                operation = @Operation(
                        operationId = "findTransaction",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(schema = @Schema(
                                                implementation = TransactionPost.class
                                        ))
                                ),
                                @ApiResponse(responseCode = "404", description = "transaction not found with" +
                                        " given id")
                        },
                        parameters = {
                                @Parameter(in = ParameterIn.PATH, name = "input")
                        }

                )

        ),
        @RouterOperation(
                path = "/router/movimiento",
                produces = {
                        MediaType.APPLICATION_JSON_VALUE
                },
                method = RequestMethod.POST,
                beanClass = HandlerTransaction.class,
                beanMethod = "saveTransaction",
                operation = @Operation(
                        operationId = "saveTransaction",
                        responses = {
                                @ApiResponse(
                                        responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(schema = @Schema(
                                                implementation = String.class
                                        ))
                                )
                        },
                        requestBody = @RequestBody(
                                content = @Content(schema = @Schema(
                                        implementation = TransactionPost.class
                                ))
                        )

                )


        )
		
		})
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
				.GET("", handlerTransaction::getTransactionAll)
				.GET("/router/product/{input}", handlerTransaction::findTransactionById)
				.POST("/router/product", handlerTransaction::saveTransaction)
				.build();
	}

}
