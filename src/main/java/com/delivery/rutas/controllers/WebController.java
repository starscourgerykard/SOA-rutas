package com.delivery.rutas.controllers;



import com.delivery.rutas.models.*;
import com.delivery.rutas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web")
public class WebController {
    
    @Autowired
    private RepartidorService repartidorService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private PaqueteService paqueteService;
    
    @Autowired
    private RutaService rutaService;
    
    // ==================== PÁGINA PRINCIPAL ====================
    @GetMapping("")
     public String index(Model model) {
        long totalRepartidores = repartidorService.obtenerTodosRepartidores().size();
        long totalClientes = clienteService.obtenerTodosClientes().size();
        long totalPaquetes = paqueteService.obtenerTodosPaquetes().size();
        long totalRutas = rutaService.obtenerTodasRutas().size();
        
        model.addAttribute("totalRepartidores", totalRepartidores);
        model.addAttribute("totalClientes", totalClientes);
        model.addAttribute("totalPaquetes", totalPaquetes);
        model.addAttribute("totalRutas", totalRutas);
        
        return "index";
    }
    
    // ==================== GESTIÓN DE REPARTIDORES ====================
    @GetMapping("/repartidores")
    public String listarRepartidores(Model model) {
        List<Repartidor> repartidores = repartidorService.obtenerTodosRepartidores();
        model.addAttribute("repartidores", repartidores);
        model.addAttribute("repartidor", new Repartidor());
        return "repartidores";
    }

    @PostMapping("/repartidores")
    public String crearRepartidor(@ModelAttribute Repartidor repartidor) {
        repartidorService.guardarRepartidor(repartidor);
        return "redirect:/web/repartidores";
    }

    @GetMapping("/repartidores/editar/{id}")
    public String mostrarFormularioEditarRepartidor(@PathVariable Long id, Model model) {
        Repartidor repartidor = repartidorService.obtenerRepartidorPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + id));
        model.addAttribute("repartidor", repartidor);
        return "editar-repartidor";
    }

    @PostMapping("/repartidores/editar/{id}")
    public String actualizarRepartidor(@PathVariable Long id, @ModelAttribute Repartidor repartidorDetalles) {
        Repartidor repartidorExistente = repartidorService.obtenerRepartidorPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + id));

        repartidorExistente.setNombre(repartidorDetalles.getNombre());
        repartidorExistente.setTelefono(repartidorDetalles.getTelefono());
        repartidorExistente.setVehiculo(repartidorDetalles.getVehiculo());
        repartidorExistente.setPlacaVehiculo(repartidorDetalles.getPlacaVehiculo());
        repartidorExistente.setActivo(repartidorDetalles.getActivo());

        repartidorService.guardarRepartidor(repartidorExistente);
        return "redirect:/web/repartidores";
    }
    
    @GetMapping("/repartidores/eliminar/{id}")
    public String eliminarRepartidor(@PathVariable Long id) {
        repartidorService.eliminarRepartidor(id);
        return "redirect:/web/repartidores";
    }
    
    // ==================== GESTIÓN DE CLIENTES ====================
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("cliente", new Cliente());
        return "clientes";
    }
    
    @PostMapping("/clientes")
    public String crearCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/web/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Cliente no válido:" + id));
        model.addAttribute("cliente", cliente);
        return "editar-cliente";
    }

    @PostMapping("/clientes/editar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute Cliente clienteDetalles) {
        Cliente clienteExistente = clienteService.obtenerClientePorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Cliente no válido:" + id));
        
        clienteExistente.setNombre(clienteDetalles.getNombre());
        clienteExistente.setDireccion(clienteDetalles.getDireccion());
        clienteExistente.setTelefono(clienteDetalles.getTelefono());
        clienteExistente.setEmail(clienteDetalles.getEmail());
        
        clienteService.guardarCliente(clienteExistente);
        return "redirect:/web/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/web/clientes";
    }
    
    // ==================== GESTIÓN DE PAQUETES ====================
    @GetMapping("/paquetes")
    public String listarPaquetes(Model model) {
        List<Paquete> paquetes = paqueteService.obtenerTodosPaquetes();
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        model.addAttribute("paquetes", paquetes);
        model.addAttribute("clientes", clientes);
        model.addAttribute("paquete", new Paquete());
        return "paquetes";
    }

    @PostMapping("/paquetes")
    public String crearPaquete(@ModelAttribute Paquete paquete, @RequestParam Long clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("ID de Cliente no válido:" + clienteId));

        paquete.setCliente(cliente);
        paqueteService.guardarPaquete(paquete);
        return "redirect:/web/paquetes";
    }

    @GetMapping("/paquetes/editar/{id}")
    public String mostrarFormularioEditarPaquete(@PathVariable Long id, Model model) {
        Paquete paquete = paqueteService.obtenerPaquetePorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Paquete no válido:" + id));
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        model.addAttribute("paquete", paquete);
        model.addAttribute("clientes", clientes);
        return "editar-paquete";
    }

    @PostMapping("/paquetes/editar/{id}")
    public String actualizarPaquete(@PathVariable Long id, @ModelAttribute Paquete paqueteDetalles, @RequestParam Long clienteId) {
        Paquete paqueteExistente = paqueteService.obtenerPaquetePorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Paquete no válido:" + id));
        Cliente cliente = clienteService.obtenerClientePorId(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("ID de Cliente no válido:" + clienteId));
        
        paqueteExistente.setCliente(cliente);
        paqueteExistente.setDestino(paqueteDetalles.getDestino());
        paqueteExistente.setDescripcion(paqueteDetalles.getDescripcion());
        paqueteExistente.setPesoKg(paqueteDetalles.getPesoKg());
        
        paqueteService.guardarPaquete(paqueteExistente);
        return "redirect:/web/paquetes";
    }

    @GetMapping("/paquetes/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        paqueteService.eliminarPaquete(id);
        return "redirect:/web/paquetes";
    }
         
    // ==================== GESTIÓN DE RUTAS ====================
    @GetMapping("/rutas")
    public String listarRutas(Model model) {
        List<Ruta> rutas = rutaService.obtenerTodasRutas();
        List<Repartidor> repartidores = repartidorService.obtenerRepartidoresActivos();
        model.addAttribute("rutas", rutas);
        model.addAttribute("repartidores", repartidores);
        model.addAttribute("ruta", new Ruta());
        return "rutas";
    }

    @PostMapping("/rutas")
    public String crearRuta(@ModelAttribute Ruta ruta, @RequestParam Long repartidorId) {
        Repartidor repartidor = repartidorService.obtenerRepartidorPorId(repartidorId)
            .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + repartidorId));

        ruta.setRepartidor(repartidor);
        rutaService.guardarRuta(ruta);
        return "redirect:/web/rutas";
    }

    @GetMapping("/rutas/editar/{id}")
    public String mostrarFormularioEditarRuta(@PathVariable Long id, Model model) {
        Ruta ruta = rutaService.obtenerRutaPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Ruta no válido:" + id));
        List<Repartidor> repartidores = repartidorService.obtenerRepartidoresActivos();
        model.addAttribute("ruta", ruta);
        model.addAttribute("repartidores", repartidores);
        return "editar-ruta";
    }

    @PostMapping("/rutas/editar/{id}")
    public String actualizarRuta(@PathVariable Long id, @ModelAttribute Ruta rutaDetalles, @RequestParam Long repartidorId) {
        Ruta rutaExistente = rutaService.obtenerRutaPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de Ruta no válido:" + id));
        Repartidor repartidor = repartidorService.obtenerRepartidorPorId(repartidorId)
            .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + repartidorId));
        
        rutaExistente.setRepartidor(repartidor);
        rutaExistente.setFecha(rutaDetalles.getFecha());
        rutaExistente.setKmEstimados(rutaDetalles.getKmEstimados());
        
        rutaService.guardarRuta(rutaExistente);
        return "redirect:/web/rutas";
    }

    @GetMapping("/rutas/eliminar/{id}")
    public String eliminarRuta(@PathVariable Long id) {
        rutaService.eliminarRuta(id);
        return "redirect:/web/rutas";
    }
  }