#!/bin/bash

# Rutas de las instalaciones de Java 8, Java 11 y Java 17
JAVA_8_HOME="/Library/Java/JavaVirtualMachines/jdk-1.8.jdk/Contents/Home"
JAVA_11_HOME="/opt/homebrew/opt/openjdk@11"
JAVA_17_HOME="/Users/jonathanfalcon/Library/Java/JavaVirtualMachines/corretto-17.0.9/Contents/Home"

# Función para cambiar la versión de Java
change_java_version() {
  if [[ $1 == "8" ]]; then
    echo "Cambiando a Java 8"
    sed -i '' '/export JAVA_HOME/d' ~/.zshrc
    echo "export JAVA_HOME=$JAVA_8_HOME" >> ~/.zshrc
  elif [[ $1 == "11" ]]; then
    echo "Cambiando a Java 11"
    sed -i '' '/export JAVA_HOME/d' ~/.zshrc
    echo "export JAVA_HOME=$JAVA_11_HOME" >> ~/.zshrc
  elif [[ $1 == "17" ]]; then
    echo "Cambiando a Java 17"
    sed -i '' '/export JAVA_HOME/d' ~/.zshrc
    echo "export JAVA_HOME=$JAVA_17_HOME" >> ~/.zshrc
  else
    echo "Versión de Java no válida. Use '8', '11' o '17'."
  fi
}

# Verificar el número de argumentos
if [ $# -eq 1 ]; then
  change_java_version $1
else
  echo "Uso: $0 [8|11|17]"
fi
