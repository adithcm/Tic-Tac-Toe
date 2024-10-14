package com.example.newproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject.databinding.ActivityGameBinding
import com.example.newproject.database.SQLiteHelper
import com.example.newproject.logic.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var board = Array(3) { Array(3) { EMPTY } }
    private var turn = PLAYER_X
    private var winner: String? = null
    private lateinit var dbHelper: SQLiteHelper
    private lateinit var preferences: SharedPreferences
    private var difficulty = "Hard"

    private val settingsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        difficulty = preferences.getString("difficulty", "Hard") ?: "Hard"
        binding.tvStatus.text = "Difficulty changed to: $difficulty"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = SQLiteHelper(this)

        preferences = getSharedPreferences("game_settings", MODE_PRIVATE)
        difficulty = preferences.getString("difficulty", "Hard") ?: "Hard"

        preferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "difficulty") {
                difficulty = sharedPreferences.getString(key, "Hard") ?: "Hard"
                binding.tvStatus.text = "Difficulty changed to: $difficulty"
            }
        }

        binding.backButton2.setOnClickListener {
            finish()
        }

        binding.tvStatus.text = "Turn: $turn"
        binding.gridLayout.let { gridLayout ->
            gameBoard = Array(3) { row ->
                Array(3) { col ->
                    val button = Button(this)
                    button.setOnClickListener { onTileClick(row, col) }
                    gridLayout.addView(button)
                    button
                }
            }
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            settingsLauncher.launch(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        preferences.unregisterOnSharedPreferenceChangeListener { _, _ -> }
    }

    private lateinit var gameBoard: Array<Array<Button>>

    private fun onTileClick(row: Int, col: Int) {
        if (board[row][col] == EMPTY && winner == null) {
            board[row][col] = turn
            gameBoard[row][col].text = turn
            winner = checkWinner(board)
            if (winner != null) {
                binding.tvStatus.text = "Winner: $winner"
                saveGameResult()
            } else {
                turn = if (turn == PLAYER_X) PLAYER_O else PLAYER_X
                binding.tvStatus.text = "Turn: $turn"
                if (turn == PLAYER_O) {
                    val aiMove = getAIMoveBasedOnDifficulty(board)
                    aiMove?.let {
                        board[it.first][it.second] = PLAYER_O
                        gameBoard[it.first][it.second].text = PLAYER_O
                        winner = checkWinner(board)
                        if (winner != null) {
                            binding.tvStatus.text = "Winner: $winner"
                            saveGameResult()
                        } else {
                            turn = PLAYER_X
                            binding.tvStatus.text = "Turn: $turn"
                        }
                    }
                }
            }
        }
    }

    private fun saveGameResult() {
        val date = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        dbHelper.insertGameResult(date, winner ?: "Draw", difficulty)
    }

    private fun getAIMoveBasedOnDifficulty(board: Array<Array<String>>): Pair<Int, Int>? {
        return when (difficulty) {
            "Easy" -> getRandomMove(board)
            "Medium" -> {
                if (Random.nextFloat() < 0.5) getRandomMove(board) else bestMove(board)
            }
            "Hard" -> bestMove(board)
            else -> bestMove(board)
        }
    }

    private fun getRandomMove(board: Array<Array<String>>): Pair<Int, Int>? {
        val emptyCells = mutableListOf<Pair<Int, Int>>()
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == EMPTY) {
                    emptyCells.add(i to j)
                }
            }
        }
        return if (emptyCells.isNotEmpty()) emptyCells.random() else null
    }
}
