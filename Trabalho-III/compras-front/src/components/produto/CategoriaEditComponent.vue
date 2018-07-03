<template>
       <b-card bg-variant="light">
           <b-card-header> {{headerCategoria}} </b-card-header>
            <b-form-group >
              <b-form-group horizontal
                    label="Nome:"
                    label-class="text-sm-right"
                    label-for="nestedStreet">
                      <b-form-input v-model="categoria.nome"></b-form-input>
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
export default class ProdutoComponent extends Vue {
    @Prop({default: null}) categoria!: Categoria | null;
    @Prop({default: null}) headerCategoria!: String;
}
</script>

<style lang="scss" scoped>
</style>
