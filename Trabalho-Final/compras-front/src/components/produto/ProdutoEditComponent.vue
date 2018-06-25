<template>

        <b-card bg-variant="light">
          <b-card-header>{{headerProduto}}</b-card-header>
              <b-form-group >
              <b-form-group horizontal
                    label="Nome:"
                    label-class="text-sm-right"
                    label-for="nestedStreet">
                      <b-form-input v-model="produtoCategoria.nome"></b-form-input>
              </b-form-group>
      <b-form-group horizontal
                    label="Preço:"
                    label-class="text-sm-right"
                    label-for="nestedCity">
        <b-form-input v-model="produtoCategoria.preco"></b-form-input>
      </b-form-group>
      <b-form-group horizontal
                    label="QTD:"
                    label-class="text-sm-right"
                    label-for="nestedState">
        <b-form-input v-model="produtoCategoria.quantidade"></b-form-input>
      </b-form-group>

      
    </b-form-group>
    
  </b-card>

</template>


<script lang="ts">
import Vue from "vue";
import { Component, Emit, Prop, Watch } from "vue-property-decorator";
import { categoriaService } from "@/services/CategoriaService";

import Categoria from "@/model/Categoria";
import Produto from "@/model/Produto";

@Component({

})
export default class ProdutoEditComponent extends Vue {
    @Prop({default: null}) categoria!: Categoria | null;
    @Prop({default: null}) produtoCategoria!: Produto | null;
        @Prop({default: null}) headerProduto!: String;

    

}
</script>

<style lang="scss" scoped>
</style>
