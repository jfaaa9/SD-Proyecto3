<template>
  <!-- Botón de cierre de sesión -->
  <button @click="logout">Cerrar Sesión</button>
  <div>
    <h1>WebSocket Chat</h1>
    <!-- Mostrar el nombre y el rol del usuario si están disponibles -->
    <p v-if="isConnected && user">
      Nombre: {{ user.username }}
      <br>
      Rol: {{ user.role }}
    </p>
    <!-- Mostrar el ID del cliente si está disponible -->
    <p v-if="isConnected && clientId">Tu ID de cliente: {{ clientId }}</p>
    <button @click="connect" :disabled="isConnected">Conectar</button>
    <button @click="disconnect" :disabled="!isConnected">Desconectar</button>
    <p>
      {{ status }}
      <span v-if="isConnected">🟢</span> <!-- Emoji verde cuando está conectado -->
      <span v-else>🔴</span> <!-- Emoji rojo cuando está desconectado -->
    </p>
    <div class="chat-box">
      <div v-for="(message, index) in messages" :key="index"
           :class="{'message-enviado': message.type === 'enviado', 
                    'message-recibido': message.type === 'recibido'}">
        {{ message.text }}
      </div>
    </div>
    <div class="message-input">
      <input 
        type="text" 
        v-model="messageText" 
        placeholder="Escribe un mensaje aquí" 
        :disabled="!isConnected"
        @keyup.enter="sendMessage"
      >
      <button @click="sendMessage" :disabled="!isConnected || !messageText">Enviar</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ws: null,
      messages: [],
      messageText: '',
      status: 'Desconectado',
      isConnected: false,
      clientId: null,
      user: null,
    };
  },
  created() {
    // Obtener los datos del usuario del localStorage al cargar la página de chat
    const userData = localStorage.getItem('userLoggedIn');
    if (userData) {
      this.user = JSON.parse(userData);
    }
    // Resto del código para la conexión WebSocket y el chat
  },
  methods: {
    connect() {
      this.ws = new WebSocket("ws://localhost:8080/ws"); // Asegúrate de usar la URL correcta

      this.ws.onopen = () => {
        this.status = "Conectado";
        this.isConnected = true;
      };

      this.ws.onmessage = (event) => {
        this.handleIncomingMessage(event.data);
      };

      this.ws.onclose = () => {
        this.status = "Desconectado";
        this.isConnected = false;
      };
    },
    disconnect() {
      this.ws.close();
    },
    sendMessage() {
      if (!this.messageText.trim()) {
        return; // No enviar si el mensaje está vacío o solo tiene espacios en blanco
      }
      this.ws.send(this.messageText);
      this.messages.push({ type: 'enviado', text: this.messageText });
      this.messageText = ''; // Limpiar el campo después de enviar
    },
    handleIncomingMessage(message) {
      if (message.startsWith("Tu ID de cliente es: ")) {
        this.clientId = message.replace("Tu ID de cliente es: ", "");
      } else {
        this.messages.push({ type: 'recibido', text: message });
      }
    },
    logout() {
        localStorage.removeItem('userLoggedIn'); // Eliminar el estado de autenticación
        this.$router.push('/'); // Redirigir al usuario a la página de inicio 
    }
  }
};
</script>

<style>
.chat-box {
  display: flex;
  flex-direction: column;
  height: 300px; /* Altura del chat */
  overflow-y: auto;
  border: 1px solid #ccc;
  margin-top: 10px;
  padding: 5px;
}

.message-enviado {
  align-self: flex-end;
  padding: 5px;
  margin: 5px;
  background-color: #58af88; /* Color de fondo para mensajes enviados */
  border-radius: 10px;
}

.message-recibido {
  align-self: flex-start;
  padding: 5px;
  margin: 5px;
  background-color: #5a5a5a; /* Color de fondo para mensajes recibidos */
  border-radius: 10px;
}

.message-input {
  display: flex;
  margin-top: 10px;
}

.message-input input[type="text"] {
  flex-grow: 1; /* Ocupa todo el espacio disponible */
  padding: 10px;
  border: 1px solid #9e9e9e;
  border-radius: 4px 0 0 4px; /* Redondea solo las esquinas izquierdas */
  border-right: none; /* Elimina el borde derecho para que se fusione con el botón */
}

.message-input button {
  padding: 10px 20px;
  border: 1px solid #ccc;
  border-radius: 0 4px 4px 0; /* Redondea solo las esquinas derechas */
  background-color: #4CAF50; /* Color de fondo */
  color: white; /* Color del texto */
  cursor: pointer; /* Cambia el cursor a tipo puntero */
}

.message-input button:disabled {
  background-color: #505050;
  cursor: default;
}

.message-input button:hover:not(:disabled) {
  background-color: #45a049; /* Color al pasar el mouse por encima, si no está deshabilitado */
}
</style>
