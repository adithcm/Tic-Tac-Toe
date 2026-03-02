# Tic-Tac-Toe Game

A modern mobile application implementing the classic Tic-Tac-Toe game for Android and iOS. The app features a single-player mode against an AI opponent with adjustable difficulty, a log of past games, and a clean, user-friendly interface. All references to class projects or course assignments have been removed for a professional, public-facing presentation.

## Features
- **Single Player Mode:** Play against an AI with three difficulty levels: Easy, Medium, and Hard.
- **Difficulty Modes:**
    - Easy: AI takes random actions.
    - Medium: AI takes random actions 50% of the time and optimal actions 50% of the time.
    - Hard: AI makes optimal moves using the Minimax algorithm with alpha-beta pruning.
- **Past Games Log:** View past game data including the date, winner, and difficulty level.
- **Human vs. Human:** Play on the same device or via Bluetooth (if supported).
- **Persistent Storage:** Game results are stored locally using SQLite.

## Getting Started

### Prerequisites
- Android Studio (for Android) or Xcode (for iOS)
- Android device/iPhone or emulator/simulator
- Kotlin (Android) or Swift (iOS)

### Build & Run
1. Clone this repository:
   ```bash
   git clone <repo-url>
   ```
2. Open the project in **Android Studio** (Android) or **Xcode** (iOS).
3. Connect your device or set up an emulator/simulator.
4. Build and run the project.

## How to Play
1. Launch the app.
2. Select **New Game** to start playing. The user always plays as 'X' and goes first, while the AI plays as 'O'.
3. Choose a difficulty level from the **Settings** screen.
4. Tap an empty space on the board to make your move. The AI will respond based on the selected difficulty.
5. After the game ends, view results in the **Past Games** screen.

## AI Logic
The AI uses the Minimax algorithm with alpha-beta pruning for optimal play at the highest difficulty. Lower difficulties introduce randomness for a more casual experience.

## Data Storage
Game results (date, winner, difficulty) are stored locally using SQLite and persist after the app is closed.

---
*This app is intended for entertainment and demonstration purposes.*
