<template>
  <div>
    <br>
    <h1>GERENCIADOR DE MERCADORIAS</h1>
    <div>
      <b-container fluid>
        <b-row>
          <b-col cols="4">
            <b-card bg-variant="light">
              <b-card-header>Selecione um Categoria</b-card-header>
              <b-form-group>
                  <b-form-select  @input="getCategoria()" v-model="categoriaSelecionada"  class="mb-3">
                    <option v-for="cat in categoriasTemplate" :key="cat.id" v-bind:value="cat">
                      {{ cat.nome}}
                    </option>
                  </b-form-select>
                  <b-container fluid>
                     <b-row>
                      <b-col cols="auto">
                    </b-col >
                  <b-col cols="auto">
                        <b-button-group>
                                 <b-btn :disabled="!categoriaSelecionada.nome" @click="deletarCategoria()" variant="danger" >Deletar</b-btn>
                                 <b-btn :disabled="!categoriaSelecionada.nome" v-b-modal="'myModalCategoriaUpdate'"  variant="warning">Alterar </b-btn>
                                 <b-btn v-b-modal="'myModalCategoria'"  variant="info">Adicionar </b-btn>
                        </b-button-group>
                   </b-col >
                   <b-col cols="auto">
                   </b-col >
                </b-row>
                </b-container>

              </b-form-group>
            </b-card>
          </b-col>
          <b-col cols="8">
            <b-card bg-variant="light">
              <div>
                <b-container fluid>
                      <b-card-header>Tabela de produtos da categoria {{categoriaSelecionada.nome}}</b-card-header>
   <b-row>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Busca" class="mb-0">
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Nome Produto" />
            <b-input-group-append>
              <b-btn :disabled="!filter" @click="filter = ''">Limpar</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Ordenar" class="mb-0">
          <b-input-group>
            <b-form-select v-model="sortBy" :options="sortOptions()">
              <option slot="first" :value="null">-- Selecione uma opção --</option>
            </b-form-select>
            <b-form-select :disabled="!sortBy" v-model="sortDesc" slot="append">
              <option :value="false">Asc</option>
              <option :value="true">Desc</option>
            </b-form-select>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
      
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Nº Produtos" class="mb-0">
          <b-form-select :options="pageOptions" v-model="perPage" />
        </b-form-group>
      </b-col>
    </b-row>

    <b-table show-empty
             stacked="md"
             :items="produtosCategoria"
             :fields="fields"
             :current-page="currentPage"
             :per-page="perPage"
             :filter="filter"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             @filtered="onFiltered"
             empty-text="Sem produtos cadastrados!"
             empty-filtered-text="Produto não cadastrado!"

    >
      <template slot="actions" slot-scope="row">
        <b-button @click="deletarProduto(row.item.id)" size="sm"  variant="danger" class="mr-1">
          Deletar
        </b-button>
       <b-button  @click="produtoSelecionado=row.item" v-b-modal="'myModalProdutoUpdate'" size="sm"  variant="warning" class="mr-1">
          Alterar
        </b-button>
      </template>
    </b-table>

    <b-row>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0" />
      </b-col>
    </b-row>


  </b-container>
              </div>
              <b-container fluid>
                <b-row>
                  <b-col></b-col>
                  <b-col></b-col>
                  <b-col>
                    <b-btn  :disabled="!categoriaSelecionada.nome" variant="info" v-b-modal="'myModalProduto'">Adicionar Produto</b-btn>
                  </b-col>
                </b-row>
              </b-container>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
  
      <div>
        <b-modal id="myModalProduto" @ok="saveProduto()">
          <ProdutoEditComponent   :headerProduto="`Adicionar produto na categoria ${categoriaSelecionada.nome}`" :categoria="categoriaSelecionada" :produtoCategoria="produtoCategoria"></ProdutoEditComponent>
        </b-modal>
            <b-modal id="myModalProdutoUpdate" @ok="updateProduto()">
          <ProdutoEditComponent  :headerProduto="`Editar produto da categoria ${categoriaSelecionada.nome}`" :categoria="categoriaSelecionada" :produtoCategoria="produtoSelecionado"></ProdutoEditComponent>
        </b-modal>
        <b-modal id="myModalCategoria" @ok="saveCategoria()">
          <CategoriaEditComponent  :headerCategoria="'Adicionar Categoria'" :categoria="categoria"></CategoriaEditComponent>
        </b-modal>
         <b-modal id="myModalCategoriaUpdate" @ok="updateCategoria()">
          <CategoriaEditComponent  :headerCategoria="`Editar Categoria ${categoriaSelecionada.nome}`" :categoria="categoriaSelecionada"></CategoriaEditComponent>
        </b-modal>
      </div>
  
    </div>
  </div>
</template>


<script lang="ts">
import Vue from "vue";
import { Component, Emit, Prop, Watch } from "vue-property-decorator";

import CategoriaEditComponent from "@/components/produto/CategoriaEditComponent.vue";

import ProdutoEditComponent from "@/components/produto/ProdutoEditComponent.vue";
import { categoriaService } from "@/services/CategoriaService";
import { produtoService } from "@/services/ProdutoService";
import Produto from "@/model/Produto";
import Categoria from "@/model/Categoria";
import CategoriaTemplate from "@/model/CategoriaTemplate";
import CategoriaTempate from "@/model/CategoriaTemplate";


@Component({
  components: {
    ProdutoEditComponent,
    CategoriaEditComponent
      }
})
export default class CategoriaGridComponent extends Vue {
  public categoriasTemplate: CategoriaTemplate[] = new Array();
  public categoriaSelecionada: Categoria = new Categoria();
  public categoria: Categoria = new Categoria();
  public produtoCategoria: Produto = new Produto();
  public produtoSelecionado: Produto = new Produto();
  public produtosCategoria: Produto[] = new  Array();
    // public titleAddCategoria:String='Adicionar Categoria'
    // public titleUpdateCategoria:String='Adicionar Categoria'
fields=[
        { key: 'nome', label: 'Nome Produto', sortable: true, sortDirection: 'desc' },
        { key: 'preco', label: 'Preço Produto', sortable: true, 'class': 'text-center' },
        { key: 'quantidade', label: 'Quantidade' },
        { key: 'actions', label: 'Ações' }
      ]
     
      currentPage= 1;
      perPage= 5;
      totalRows= this.produtosCategoria.length;
      pageOptions= [ 5, 10, 15 ];
      sortBy= null;
      sortDesc= false;
      filter= null;
      sortDirection= 'asc';
      modalInfo={ title: '', content: '' }

  created() {
    this.getCategorias();
  }

  getCategorias() {
    categoriaService.getall().then(r => {
      this.categoriasTemplate = r.data;
      if(this.categoriasTemplate.length>0){
      this.categoriaSelecionada = <Categoria>this.categoriasTemplate[0];
    }
    });
  }
   getCategoria() {
     if(this.categoriaSelecionada.nome!=""){
       console.log(this.categoriaSelecionada.id)
        categoriaService.get(this.categoriaSelecionada.id).then(r => {
        this.produtosCategoria = r.data.produtos;
        });
     }
   
  }
  deletarCategoria() {
    categoriaService.delete(this.categoriaSelecionada.id).then(r => {
     this.getCategorias();
     this.categoriaSelecionada=new Categoria()
     this.produtosCategoria=new Array<Produto>()
    });
  }
  deletarProduto(id: number) {
    produtoService.delete(id).then(r => {
     this.getCategoria();
    });
  }
  saveProduto(method:String) {
      produtoService.save(this.categoriaSelecionada.id,this.produtoCategoria).then(data => {
          this.produtosCategoria.push(data.data)
          this.produtoCategoria=new Produto()
      });
  }
  updateProduto() {
        produtoService.update(this.categoriaSelecionada.id,this.produtoSelecionado).then(data => {
          console.log(data.data      )
          this.produtoSelecionado=data.data      
      });
  }
  updateCategoria() {
       if (this.categoriaSelecionada.nome != "") {
          categoriaService.update(this.categoriaSelecionada).then(r => {
              this.getCategorias()
            });
       }
  }


   saveCategoria(method:String) {

          if (this.categoria.nome != "") {
            categoriaService.add(this.categoria).then(r => {
              this.categoriasTemplate.push(r.data);
              this.categoriaSelecionada=r.data
              this.categoria= new Categoria()
              
            });   
  }
  }
  
    sortOptions () {
      return this.fields
        .filter(f => f.sortable)
        .map(f => { return { text: f.label, value: f.key } })
    }

     info (item:any, index:any, button:any) {
      this.modalInfo.title = `Row index: ${index}`
      this.modalInfo.content = JSON.stringify(item, null, 2)
      this.$root.$emit('bv::show::modal', 'modalInfo', button)
    }
    resetModal () {
      this.modalInfo.title = ''
      this.modalInfo.content = ''
    }
    onFiltered (filteredItems:any) {
      this.totalRows = filteredItems.length
      this.currentPage = 1
    }

  
}
</script>

<style lang="scss" scoped>
.btn-add-left {
  margin-right: 50px;
}
</style>

