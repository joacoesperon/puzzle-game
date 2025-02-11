# Puzzle Game in Java

An interactive puzzle game developed in Java that allows users to solve puzzles with different images.

## 🎮 Features

- **Multiple difficulty levels**: Allows selecting different puzzle divisions (from 2x2 to 10x10)
- **Scoring system**: Players earn points based on the time taken to complete the puzzle
- **Game history**: Saves and displays a record of all played games
- **Customization**: Allows changing the image directory to use your own images
- **Intuitive interface**: Includes:
  - Menu bar
  - Toolbar with icons
  - Button panel
  - Remaining time display
  - Full image preview

## 🛠️ System Requirements

- Java JDK 15 or higher
- Java-compatible operating system (Windows, Linux, MacOS)

## 📥 Installation

1. Clone the repository:

```bash
git clone https://github.com/joacoesperon/puzzle-game
```

2. Open the project in NetBeans or your preferred IDE

3. Compile and run the `Principal.java` file

## 🎯 How to Play

1. Start the game
2. Select "NEW GAME" from the menu
3. Enter your name and select the number of horizontal and vertical divisions
4. Click on pieces to swap their positions
5. Complete the puzzle before time runs out
6. Get the highest score possible!

## 📁 Project Structure

```plaintext
src/
├── practica/
│   ├── Principal.java # Main game class
│   ├── SubImagen.java # Handles puzzle pieces
│   └── lecturaDatos.java # Manages data input
├── paneles/
│   ├── PanelPartida.java # Main game panel
│   ├── PanelHistorial.java # Displays game history
│   └── PanelImagenSolucion.java # Shows the solution
└── partida/
    ├── Partida.java # Game logic
    └── PartidaFileIn.java # File management
```

## 🤝 Contributing

Contributions are welcome. To contribute:

1. Fork the project
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

## ✍️ Author

- Joaquín Esperón

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## 🎓 Acknowledgments

- Developed as a project for the Programming II course at UIB
- Icons and images included in the `iconos/` and `imagenes/` directories

