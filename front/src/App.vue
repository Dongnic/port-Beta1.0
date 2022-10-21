<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
const theme = localStorage.getItem('theme')
if (!theme) {
  localStorage.setItem('theme', 'light')
  document.documentElement.setAttribute('data-theme', 'light')
} else { document.documentElement.setAttribute('data-theme', theme) }
export default {
  name: 'App',
  data: () => ({
    //
  })
}
</script>
<style>
#v-app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
