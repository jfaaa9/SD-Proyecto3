<template>
  <div class="login-container">
    <div class="login-content">
      <form @submit.prevent="login" class="login-form">
        <h2>Iniciar Sesión</h2>
        <div class="form-group">
          <label for="username">Nombre de Usuario:</label>
          <input
            type="text"
            id="username"
            v-model="username"
            class="form-control"
          />
        </div>
        <div class="form-group">
          <label for="password">Contraseña:</label>
          <input
            type="password"
            id="password"
            v-model="password"
            class="form-control"
          />
        </div>
        <button type="submit" class="btn-submit">Iniciar Sesión</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      axios
        //.post("http://localhost:8081/login", {
        //.post("http://api:8081/login", {
        .post("http://34.176.210.55:8081/login", {
          username: this.username,
          password: this.password,
        })
        .then((response) => {
          if (response.status === 200) {
            // Almacenar los datos del usuario en el localStorage
            localStorage.setItem("userLoggedIn", JSON.stringify(response.data));
            // Redirigir al usuario a la página de chat
            this.$router.push("/chat");
          } else {
            // Manejar otros casos de respuesta aquí
          }
        })
        .catch((error) => {
          console.error("Error de autenticación:", error);
          // Manejar el error de inicio de sesión aquí (por ejemplo, mostrar un mensaje)
        });
    },
  },
};
</script>

<style>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* Esto centra verticalmente en la pantalla */
}

.login-content {
  max-width: 700px; /* Ancho máximo del formulario */
  padding: 60px; /* Aumentar el espacio interno */
  border: 1px solid #f3f3f3;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(182, 182, 182, 0.1);
  background-color: #f0f0f0;
}

.login-form h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-control {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.btn-submit {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.btn-submit:hover {
  background-color: #0056b3;
}
</style>
