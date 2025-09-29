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
    
    // Página principal
    @GetMapping("")
    public String index(Model model) {
        // Obtener conteos para el dashboard
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
    
    // Gestión de Repartidores
    @GetMapping("/repartidores")
    public String listarRepartidores(Model model) {
        List<Repartidor> repartidores = repartidorService.obtenerTodosRepartidores();
        model.addAttribute("repartidores", repartidores);
        model.addAttribute("repartidor", new Repartidor());
        return "repartidores";
    }

  // EN @GetMapping("/repartidores/editar/{id}")
  @GetMapping("/repartidores/editar/{id}")
  public String mostrarFormularioEditarRepartidor(@PathVariable Long id, Model model) {
    Repartidor repartidor = repartidorService.obtenerRepartidorPorId(id)
      .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + id));
    model.addAttribute("repartidor", repartidor);
    return "editar-repartidor"; // Necesitarás crear esta vista HTML.
  }

  @PostMapping("/repartidores/editar/{id}")
  public String actualizarRepartidor(@PathVariable Long id, @ModelAttribute Repartidor repartidorDetalles) {
    // Obtenemos el repartidor existente de la base de datos
    Repartidor repartidorExistente = repartidorService.obtenerRepartidorPorId(id)
      .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + id));

    // Actualizamos los campos del objeto existente con los datos del formulario
    repartidorExistente.setNombre(repartidorDetalles.getNombre());
    repartidorExistente.setTelefono(repartidorDetalles.getTelefono());
    repartidorExistente.setVehiculo(repartidorDetalles.getVehiculo());
    repartidorExistente.setPlacaVehiculo(repartidorDetalles.getPlacaVehiculo());
    repartidorExistente.setActivo(repartidorDetalles.getActivo());

    // Guardamos el objeto actualizado
    repartidorService.guardarRepartidor(repartidorExistente);

    return "redirect:/web/repartidores";
  }
    
    @GetMapping("/repartidores/eliminar/{id}")
    public String eliminarRepartidor(@PathVariable Long id) {
        repartidorService.eliminarRepartidor(id);
        return "redirect:/web/repartidores";
    }
    
    // Gestión de Clientes (similar a repartidores)
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
    
    // Gestión de Paquetes
    @GetMapping("/paquetes")
    public String listarPaquetes(Model model) {
        List<Paquete> paquetes = paqueteService.obtenerTodosPaquetes();
        List<Cliente> clientes = clienteService.obtenerTodosClientes();
        model.addAttribute("paquetes", paquetes);
        model.addAttribute("clientes", clientes);
        model.addAttribute("paquete", new Paquete());
        return "paquetes";
    }


  // EN @PostMapping("/paquetes")
  @PostMapping("/paquetes")
  public String crearPaquete(@ModelAttribute Paquete paquete, @RequestParam Long clienteId) {
    // Hacemos lo mismo para el cliente
    Cliente cliente = clienteService.obtenerClientePorId(clienteId)
      .orElseThrow(() -> new IllegalArgumentException("ID de Cliente no válido:" + clienteId));

    paquete.setCliente(cliente);
    paqueteService.guardarPaquete(paquete);
    return "redirect:/web/paquetes";
  }
         
    
    // Gestión de Rutas
    @GetMapping("/rutas")
    public String listarRutas(Model model) {
        List<Ruta> rutas = rutaService.obtenerTodasRutas();
        List<Repartidor> repartidores = repartidorService.obtenerRepartidoresActivos();
        model.addAttribute("rutas", rutas);
        model.addAttribute("repartidores", repartidores);
        model.addAttribute("ruta", new Ruta());
        return "rutas";
    }


  // EN @PostMapping("/rutas")
  @PostMapping("/rutas")
  public String crearRuta(@ModelAttribute Ruta ruta, @RequestParam Long repartidorId) {
    // "Saca" el repartidor del Optional. Si no existe, lanza una excepción.
    Repartidor repartidor = repartidorService.obtenerRepartidorPorId(repartidorId)
      .orElseThrow(() -> new IllegalArgumentException("ID de Repartidor no válido:" + repartidorId));

    ruta.setRepartidor(repartidor); // ¡Ahora sí funciona!
    rutaService.guardarRuta(ruta);
    return "redirect:/web/rutas";
  }

  // Agrega este método DENTRO de la clase WebController
  @PostMapping("/repartidores")
  public String crearRepartidor(@ModelAttribute Repartidor repartidor) {
    repartidorService.guardarRepartidor(repartidor);
    return "redirect:/web/repartidores";
  }
}
