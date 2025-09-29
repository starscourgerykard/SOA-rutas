package com.delivery.rutas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.rutas.models.Cliente;
import com.delivery.rutas.repositories.IClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private IClienteRepository clienteRepository;
    
    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
