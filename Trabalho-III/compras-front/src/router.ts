import Vue from 'vue'
import Router from 'vue-router'
import CategoriaGrid from '@/components/CategoriaGrid.vue';
import BootstrapVue from 'bootstrap-vue'
Vue.use(Router)
Vue.use(BootstrapVue);
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
export default new Router({
  routes: [
      {
          path: '/',
          name: 'categoria',
          component: CategoriaGrid
      }
  ]
})
