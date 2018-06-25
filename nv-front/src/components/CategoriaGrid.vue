<template>
  <div>
    <br>
    <h1>GERENCIADOR DE MERCADORIAS</h1>
    <div>
      <b-container fluid>
        <b-row>
          <b-col cols="4">
            <b-card bg-variant="light">
              <b-form-group>
                <b-form-select v-model="categoriaSelecionada" class="mb-3">
                  <option v-for="cat in categorias" :key="cat.id" v-bind:value="cat">
                    {{ cat.nome}}
                  </option>
                </b-form-select>
                  <b-container fluid>
                     <b-row>
                      <b-col cols="auto">
                  
                                             </b-col >
              <b-col cols="auto">
                <b-button-group>
                                                                  <b-btn @click="deletarCategoria()" variant="danger" >Deletar</b-btn>
                                 <b-btn v-b-modal="'myModalCategoria'"  variant="warning">Alterar </b-btn>
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
    <!-- User Interface controls -->
    <b-row>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Filter" class="mb-0">
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Type to Search" />
            <b-input-group-append>
              <b-btn :disabled="!filter" @click="filter = ''">Clear</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Sort" class="mb-0">
          <b-input-group>
            <b-form-select v-model="sortBy" :options="sortOptions">
              <option slot="first" :value="null">-- none --</option>
            </b-form-select>
            <b-form-select :disabled="!sortBy" v-model="sortDesc" slot="append">
              <option :value="false">Asc</option>
              <option :value="true">Desc</option>
            </b-form-select>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Sort direction" class="mb-0">
          <b-input-group>
            <b-form-select v-model="sortDirection" slot="append">
              <option value="asc">Asc</option>
              <option value="desc">Desc</option>
              <option value="last">Last</option>
            </b-form-select>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Per page" class="mb-0">
          <b-form-select :options="pageOptions" v-model="perPage" />
        </b-form-group>
      </b-col>
    </b-row>

    <!-- Main table element -->
    <b-table show-empty
             stacked="md"
             :items="items"
             :fields="fields"
             :current-page="currentPage"
             :per-page="perPage"
             :filter="filter"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             :sort-direction="sortDirection"
             @filtered="onFiltered"
    >
      <template slot="name" slot-scope="row">{{row.value.first}} {{row.value.last}}</template>
      <template slot="isActive" slot-scope="row">{{row.value?'Yes :)':'No :('}}</template>
      <template slot="actions" slot-scope="row">
        <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
        <b-button size="sm" @click.stop="info(row.item, row.index, $event.target)" class="mr-1">
          Info modal
        </b-button>
        <b-button size="sm" @click.stop="row.toggleDetails">
          {{ row.detailsShowing ? 'Hide' : 'Show' }} Details
        </b-button>
      </template>
      <template slot="row-details" slot-scope="row">
        <b-card>
          <ul>
            <li v-for="(value, key) in row.item" :key="key">{{ key }}: {{ value}}</li>
          </ul>
        </b-card>
      </template>
    </b-table>

    <b-row>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0" />
      </b-col>
    </b-row>

    <!-- Info modal -->
    <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" ok-only>
      <pre>{{ modalInfo.content }}</pre>
    </b-modal>

  </b-container>
              </div>
              <b-container fluid>
                <b-row>
                  <b-col></b-col>
                  <b-col></b-col>
                  <b-col>
                    <b-btn v-b-modal="'myModalProduto'">Adicionar Produto</b-btn>
                  </b-col>
                </b-row>
              </b-container>
            </b-card>
          </b-col>
        </b-row>
      </b-container>
  
      <div>
        <b-modal id="myModalProduto" @ok="saveProduto">
          <ProdutoEditComponent :categoriaSelecionada="categoriaSelecionada" :novoProdutoCategoria="novoProdutoCategoria"></ProdutoEditComponent>
        </b-modal>
        <b-modal id="myModalCategoria" @ok="saveCategoria">
          <CategoriaEditComponent :novaCategoria="novaCategoria"></CategoriaEditComponent>
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
import { categoriaService } from "../services/CategoriaService";
import { produtoService } from "../services/ProdutoService";
import Produto from "../model/Produto";
import Categoria from "../model/Categoria";
import CategoriaTemplate from "../model/CategoriaTemplate";


@Component({
  components: {
    ProdutoEditComponent,
    CategoriaEditComponent
      }
})
export default class CategoriaGridComponent extends Vue {
  public categoriasTemplate: CategoriaTemplate[] = new Array();
  public categoriaSelecionada: Categoria = new Categoria();
  public novaCategoria: Categoria = new Categoria();
  public novoProdutoCategoria: Produto = new Produto();
  public produtosCategoria: Produto[] = new  Array();


      fields= [
        { key: 'nome', label: 'Nome Produto', sortable: true, sortDirection: 'desc' },
        { key: 'preco', label: 'preco', sortable: true, 'class': 'text-center' },
        { key: 'quantidade', label: 'QTD', sortable: true, 'class': 'text-center'  },
        { key: 'actions', label: 'Ações' }
      ]
      currentPage= 1;
      perPage= 5;
      totalRows= this.produtosCategoria.length;
      pageOptions= [ 5, 10, 15 ];
      sortBy= null;
      sortDesc= false;
      sortDirection= 'asc';
      filter= null;
      modalInfo= { title: '', content: '' }
    

  created() {
    this.getCategorias();
  }

  getCategorias() {
    categoriaService.get().then(r => {
      this.categoriasTemplate = r.data;
      if(this.categoriasTemplate.length>0){
      this.categoriaSelecionada = <Categoria>this.categoriasTemplate[0];
    }
    });
  }
  deletarCategoria() {
    categoriaService.delete(this.categoriaSelecionada.id).then(r => {
      this.categoriasTemplate = r.data;
    });
  }
  saveProduto() {
    this.categoriaSelecionada.produtos.push(this.novoProdutoCategoria);
    categoriaService.add(this.categoriaSelecionada).then(data => {
      console.log(data.data);
        // this.categorias.filter(r => {
        //   if (r.id == data.data.id) {
        //     r = data.data;
        //   }
        // });
    });
  }
  saveCategoria() {
    if (this.novaCategoria.nome != "") {
      categoriaService.add(this.novaCategoria).then(r => {
        this.categoriasTemplate.push(r.data);
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.btn-add-left {
  margin-right: 50px;
}
</style>
