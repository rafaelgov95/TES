package br.com.analise.compras.resource;


import br.com.analise.compras.Entity.Categoria;
import br.com.analise.compras.Entity.Pedido;
import br.com.analise.compras.Entity.Produto;
import br.com.analise.compras.service.CategoriaService;
import br.com.analise.compras.service.PedidoService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/produto")
@CrossOrigin("http://localhost:8080")
public class PedidoResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CategoriaService categoriaService;




}
