package com.socialnetwork.microservice.service.cliente;

import com.socialnetwork.microservice.entity.Clientes;

public interface ClientesService {

	Clientes guardarCliente(Clientes id);

	Clientes consultarCliente(String id);

	void borrarCliente(String id);

}
