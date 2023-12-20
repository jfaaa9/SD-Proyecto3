<template>
    <div class="home-page">
      <h1>Bienvenido a la Aplicación</h1>
      <button @click="goToLogin">Iniciar Sesión</button>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      goToLogin() {
        this.$router.push('/login');
      }
    }
  };
  </script>
  
  <style>
  /* Estilos para la página principal */
  .home-page {
    text-align: center;
    margin-top: 50px;
  }
  </style>
  