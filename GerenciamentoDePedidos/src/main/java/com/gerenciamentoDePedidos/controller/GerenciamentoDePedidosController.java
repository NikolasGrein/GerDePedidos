package com.gerenciamentoDePedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoDePedidos.entities.GerenciamentoDePedidos;
import com.gerenciamentoDePedidos.service.GerenciamentoDePedidosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "GerenciamentoDePedidos", description = "API REST DE GERENCIAMENTO DE PEDIDOS")
@RestController
@RequestMapping("/gerenciamentoDePedidos")
public class GerenciamentoDePedidosController {
	
	private final GerenciamentoDePedidosService PedidosService;
	
	@Autowired
    public GerenciamentoDePedidosController(GerenciamentoDePedidosService gerenciamentoDePedidosService) {
        this.PedidosService = gerenciamentoDePedidosService;
    }
	
	@GetMapping("/{id}")
	@Operation(summary = "Localiza gerenciamento de pedidos por ID")
    public ResponseEntity<GerenciamentoDePedidos> buscaGerenciamentoDePedidosControlId(@PathVariable Long id) {
		GerenciamentoDePedidos gerenciamentoDePedidos = PedidosService.buscaGerenciamentoDePedidosId(id); 
        if (gerenciamentoDePedidos != null) {
            return ResponseEntity.ok(gerenciamentoDePedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
        
        @GetMapping("/")
        @Operation(summary = "Apresenta todos os gerenciamentos de pedidos")
        public ResponseEntity<List<GerenciamentoDePedidos>> buscaTodosGerenciamentoDePedidosControl() { 
            List<GerenciamentoDePedidos> gerenciamentoDePedidos = PedidosService.buscaTodosGerenciamentoDePedidos();
            return ResponseEntity.ok(gerenciamentoDePedidos);

    }
        
        @PostMapping("/")
        public ResponseEntity<GerenciamentoDePedidos> salvaGerenciamentoDePedidosControl(@RequestBody GerenciamentoDePedidos gerenciamentoDePedidos) { 
        	GerenciamentoDePedidos salvaGerenciamentoDePedidos = PedidosService.salvaGerenciamentoDePedidos(gerenciamentoDePedidos);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvaGerenciamentoDePedidos);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Altera um gerenciamento de pedidos")
        public ResponseEntity<GerenciamentoDePedidos> alteraGerenciamentoDePedidosControl(@PathVariable Long id, @RequestBody GerenciamentoDePedidos gerenciamentoDePedidos) { 
        	GerenciamentoDePedidos alteraGerenciamentoDePedidos = PedidosService.alterarGerenciamentoDePedidos(id, gerenciamentoDePedidos);
            if (alteraGerenciamentoDePedidos != null) {
                return ResponseEntity.ok(gerenciamentoDePedidos);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Exclui um gerenciamento de pedidos")
        public ResponseEntity<String> apagaGerenciamentoDePedidosControl(@PathVariable Long id) { 
            boolean apagar = PedidosService.apagarGerenciamentoDePedidos(id);
            if (apagar) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}