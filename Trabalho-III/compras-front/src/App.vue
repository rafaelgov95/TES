<template>
  <div id="app">
              <b-navbar toggleable="md" type="dark" variant="info">

            <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

            <b-navbar-brand href="#">Atacadão Ching Ling Ltd </b-navbar-brand>

            <b-collapse is-nav id="nav_collapse">

              <b-navbar-nav>
                <b-nav-item href="#"></b-nav-item>
                <b-nav-item href="#" disabled></b-nav-item>
              </b-navbar-nav>

              <!-- Right aligned nav items -->
              <b-navbar-nav class="ml-auto">

                <b-nav-form>
                  <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Informática"/>
                  <b-button size="sm" class="my-2 my-sm-0" type="submit">Procurar</b-button>
                </b-nav-form>

                <b-nav-item-dropdown text="Lang" right>
                  <b-dropdown-item href="#">EN</b-dropdown-item>
                  <b-dropdown-item href="#">ES</b-dropdown-item>
                  <b-dropdown-item href="#">RU</b-dropdown-item>
                  <b-dropdown-item href="#">FA</b-dropdown-item>
                </b-nav-item-dropdown>

                <b-nav-item-dropdown right>
                  <!-- Using button-content slot -->
                  <template slot="button-content">
                    <em>Usuario</em>
                  </template>
                  <b-dropdown-item href="#">Perfil</b-dropdown-item>
                  <b-dropdown-item href="#">Sair</b-dropdown-item>
                </b-nav-item-dropdown>
              </b-navbar-nav>

            </b-collapse>
          </b-navbar>
    <router-view/>
  </div>
</template>

<style lang="scss">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
