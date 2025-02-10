# Puzzle Game en Java

Un juego de puzzle interactivo desarrollado en Java que permite a los usuarios resolver rompecabezas con diferentes imágenes.

## 🎮 Características

- **Múltiples niveles de dificultad**: Permite seleccionar diferentes divisiones del puzzle (2x2 hasta 10x10)
- **Sistema de puntuación**: Los jugadores obtienen puntos basados en el tiempo que tardan en completar el puzzle
- **Historial de partidas**: Guarda y muestra el registro de todas las partidas jugadas
- **Personalización**: Permite cambiar el directorio de imágenes para usar tus propias imágenes
- **Interfaz intuitiva**: Incluye:
  - Barra de menú
  - Barra de herramientas con iconos
  - Panel de botones
  - Visualización del tiempo restante
  - Vista previa de la imagen completa

## 🛠️ Requisitos del Sistema

- Java JDK 15 o superior
- Sistema operativo compatible con Java (Windows, Linux, MacOS)

## 📥 Instalación

1. Clona el repositorio:

git clone https://github.com/joacoesperon/puzzle-game

2. Abre el proyecto en NetBeans o tu IDE preferido

3. Compila y ejecuta el archivo `Principal.java`

## 🎯 Cómo Jugar

1. Inicia el juego
2. Selecciona "NUEVA PARTIDA" desde el menú
3. Ingresa tu nombre y selecciona el número de divisiones horizontales y verticales
4. Haz clic en las piezas para intercambiar sus posiciones
5. Completa el puzzle antes de que se acabe el tiempo
6. ¡Consigue la mayor puntuación posible!

## 📁 Estructura del Proyecto

src/
├── practica/
│ ├── Principal.java # Clase principal del juego
│ ├── SubImagen.java # Manejo de las piezas del puzzle
│ └── lecturaDatos.java # Gestión de entrada de datos
├── paneles/
│ ├── PanelPartida.java # Panel principal del juego
│ ├── PanelHistorial.java # Visualización del historial
│ └── PanelImagenSolucion.java # Muestra la solución
└── partida/
├── Partida.java # Lógica de la partida
└── PartidaFileIn.java # Gestión de archivos


## 🤝 Contribuir

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un Fork del proyecto
2. Crea una nueva rama (`git checkout -b feature/AmazingFeature`)
3. Realiza tus cambios
4. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
5. Push a la rama (`git push origin feature/AmazingFeature`)
6. Abre un Pull Request

## ✍️ Autores

- Joaquín Esperón

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## 🎓 Reconocimientos

- Desarrollado como proyecto para la asignatura de Programación II en la UIB
- Iconos e imágenes incluidas en el directorio `iconos/` e `imagenes/`