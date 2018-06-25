package br.com.analise.compras.resource;

import br.com.analise.compras.Entity.Produto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.analise.compras.Entity.Categoria;
import br.com.analise.compras.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/categoria")
@CrossOrigin("http://localhost:8080")
public class CategoriaResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CategoriaService categoriaService;

    @Transactional
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable("id") Long id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(categoriaService.getCategorias());
    }

    @RequestMapping(method = RequestMethod.PUT,  path = "/")
    public ResponseEntity<?> update(@RequestBody Categoria categoria) {
        Categoria cat = categoriaService.salvar(categoria);
        return ResponseEntity.ok().body(cat);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(categoriaService.delete(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Categoria categoria) {
        Categoria cat = categoriaService.salvar(categoria);
        return ResponseEntity.ok().body(cat);
    }
}
