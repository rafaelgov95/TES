package br.com.analise.compras.resource;


import br.com.analise.compras.Entity.Categoria;
import br.com.analise.compras.Entity.Produto;
import br.com.analise.compras.service.CategoriaService;
import br.com.analise.compras.service.ProdutoService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
public class ProdutoResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CategoriaService categoriaService;

//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<?> update(@RequestBody Produto produto) {
//        return ResponseEntity.ok().body( produtoService.salvar(produto));
//    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestParam Long id , @RequestBody Produto produto) {
        Categoria cat = categoriaService.buscarCategoriaPorId(id);
        produto.getCategorias().add(cat);
        return ResponseEntity.ok().body( produtoService.salvar(produto));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> savar(@RequestParam Long id , @RequestBody Produto produto) {
        Categoria cat = categoriaService.buscarCategoriaPorId(id);
        produto.getCategorias().add(cat);
        return ResponseEntity.ok().body( produtoService.salvar(produto));
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok(200);
    }

}
