# Tic-Tac-Toe Game

## Project Overview

This project is a mobile application that implements the classic Tic-Tac-Toe game, developed for Android/iOS as part of the CSE 535 Mobile Computing course. The game allows a human player to play against an AI opponent, with the AI's difficulty controlled using the Minimax algorithm with alpha-beta pruning. Players can adjust the game's difficulty and view past game results. The game is designed to operate on mobile platforms, optimizing for limited resources.

### Features
- **Single Player Mode**: Play against the AI with three difficulty levels: Easy, Medium, and Hard.
- **Difficulty Modes**:
    - Easy: AI takes random actions.
    - Medium: AI takes random actions 50% of the time and optimal actions 50% of the time.
    - Hard: AI makes optimal moves using the Minimax algorithm with alpha-beta pruning.
- **Past Games Log**: View past game data including the date, winner, and difficulty level.
- **Extra Credit (Optional)**: Play against another human using Bluetooth connectivity across two devices or play on the same device in human-vs-human mode.

## Installation

### Prerequisites
- Android Studio or Xcode (depending on platform)
- Android Phone/iPhone
- For Android Studio: A computer running Ubuntu/Windows/Mac; for Xcode: a Mac.
- Kotlin (for Android) or Swift (for iOS)

### Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/adithcm/CSE535-Project2.git
2. Open the project in **Android Studio** (for Android) or **Xcode** (for iOS).
3. Connect your phone via USB or set up an emulator in your IDE.
4. Build and run the project on the connected/emulated device.

### Usage

1. Launch the app.
2. Select **New Game** to start playing. The user always plays as 'X' and goes first, while the AI plays as 'O'.
3. Select a difficulty level (Easy, Medium, Hard) from the **Settings** screen.
4. Play by tapping an empty space on the game board. The AI will make a move based on the selected difficulty level.
5. Once the game ends, you can view the result on the **Past Games** screen, where the date, winner, and difficulty are stored.
6. *(Extra Credit)*: If playing in human-vs-human mode, connect via Bluetooth to another device, and play in real-time with another player.

### Minimax Algorithm with Alpha-Beta Pruning

The AI uses the Minimax algorithm to make optimal moves. Alpha-beta pruning is implemented to optimize the decision-making process by reducing the number of nodes that are evaluated.

### Storing Game Data

The app stores past game data (date, winner, and difficulty mode) on the device using SQLite or DataStore. This data persists even after the app is closed.

### Contribution Guidelines

- Each team member should work on a separate branch and frequently push updates to GitHub.
- Use descriptive commit messages and proper version control practices.

### License

This project is licensed under the MIT License.
