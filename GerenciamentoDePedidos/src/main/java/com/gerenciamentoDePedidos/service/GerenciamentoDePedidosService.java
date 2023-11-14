package com.gerenciamentoDePedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoDePedidos.entities.GerenciamentoDePedidos;
import com.gerenciamentoDePedidos.repository.GerenciamentoDePedidosRepository;

@Service
public class GerenciamentoDePedidosService {

    private final GerenciamentoDePedidosRepository gerenciamentoDePedidosRepository;

    @Autowired
    public GerenciamentoDePedidosService(GerenciamentoDePedidosRepository gerenciamentoDePedidosRepository) {
        this.gerenciamentoDePedidosRepository = gerenciamentoDePedidosRepository;
    }

    public List<GerenciamentoDePedidos> buscaTodosGerenciamentoDePedidos(){
        return gerenciamentoDePedidosRepository.findAll();
    }

    public GerenciamentoDePedidos buscaGerenciamentoDePedidosId(Long id) {
        Optional <GerenciamentoDePedidos> GerenciamentoDePedidos = gerenciamentoDePedidosRepository.findById(id);
        return GerenciamentoDePedidos.orElse(null);
    }

    public GerenciamentoDePedidos salvaGerenciamentoDePedidos (GerenciamentoDePedidos gerenciamentoDePedidos) {
        return gerenciamentoDePedidosRepository.save(gerenciamentoDePedidos);
    }

    public GerenciamentoDePedidos alterarGerenciamentoDePedidos(Long id, GerenciamentoDePedidos alterarGerenciamentoDePedidos) {
        Optional <GerenciamentoDePedidos> existeGerenciamentoDePedidos = gerenciamentoDePedidosRepository.findById(id);
        if (existeGerenciamentoDePedidos.isPresent()) {
            alterarGerenciamentoDePedidos.setId(id);
            return gerenciamentoDePedidosRepository.save(alterarGerenciamentoDePedidos);
        }
        return null;
    }

        public boolean apagarGerenciamentoDePedidos (Long id) {
            Optional <GerenciamentoDePedidos> existeGerenciamentoDePedidos = gerenciamentoDePedidosRepository.findById(id);
            if (existeGerenciamentoDePedidos.isPresent()) {
            	gerenciamentoDePedidosRepository.deleteById(id);
                return true;
            }
            return false;
        }

    }