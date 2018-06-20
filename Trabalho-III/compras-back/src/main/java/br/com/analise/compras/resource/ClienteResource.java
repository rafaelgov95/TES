package br.com.analise.compras.resource;


import br.com.analise.compras.Entity.Categoria;
import br.com.analise.compras.Entity.Cliente;
import br.com.analise.compras.service.CategoriaService;
import br.com.analise.compras.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin("http://localhost:8081")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok().body(cliente);
    }
}
