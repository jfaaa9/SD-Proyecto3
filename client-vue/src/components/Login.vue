<template>
    <div class="login-container">
        <form @submit.prevent="login" class="login-form">
            <h2>Iniciar Sesión</h2>
            <div class="form-group">
                <label for="username">Nombre de Usuario:</label>
                <input type="text" id="username" v-model="username" class="form-control">
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" v-model="password" class="form-control">
            </div>
            <button type="submit" class="btn-submit">Iniciar Sesión</button>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            username: '',
            password: ''
        };
    },
    methods: {
        login() {
            axios.post('http://localhost:8081/login', {
                username: this.username,
                password: this.password
            })
            .then(response => {
                if (response.status === 200) {
                    // Almacenar los datos del usuario en el localStorage
                    localStorage.setItem('userLoggedIn', JSON.stringify(response.data));
                    // Redirigir al usuario a la página de chat
                    this.$router.push('/chat');
                } else {
                    // Manejar otros casos de respuesta aquí
                }
            })
            .catch(error => {
                console.error('Error de autenticación:', error);
                // Manejar el error de inicio de sesión aquí (por ejemplo, mostrar un mensaje)
            });
        },
    }
};
</script>
  
<style>
.login-container {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
  