package com.example.tic_tac_toe.logic

// Constants representing the players and empty cells
const val PLAYER_X = "X"
const val PLAYER_O = "O"
const val EMPTY = " "

// Check for the winner or if the game is still ongoing
fun checkWinner(board: Array<Array<String>>): String? {
    // Check rows, columns, and diagonals for a win
    for (i in 0..2) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY) return board[i][0]
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != EMPTY) return board[0][i]
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) return board[0][0]
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY) return board[0][2]

    // Check for draw
    if (board.flatten().none { it == EMPTY }) return "Draw"
    return null
}

// Minimax Algorithm with Alpha-Beta Pruning for AI
fun minimax(board: Array<Array<String>>, depth: Int, isMaximizing: Boolean, alpha: Int, beta: Int): Int {
    val winner = checkWinner(board)
    if (winner == PLAYER_O) return 10 - depth
    if (winner == PLAYER_X) return depth - 10
    if (winner == "Draw") return 0

    var bestScore = if (isMaximizing) Int.MIN_VALUE else Int.MAX_VALUE
    var a = alpha
    var b = beta

    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == EMPTY) {
                board[i][j] = if (isMaximizing) PLAYER_O else PLAYER_X
                val score = minimax(board, depth + 1, !isMaximizing, a, b)
                board[i][j] = EMPTY
                if (isMaximizing) {
                    bestScore = maxOf(bestScore, score)
                    a = maxOf(a, score)
                } else {
                    bestScore = minOf(bestScore, score)
                    b = minOf(b, score)
                }
                if (b <= a) return bestScore
            }
        }
    }
    return bestScore
}

// AI chooses the best move using Minimax
fun bestMove(board: Array<Array<String>>): Pair<Int, Int>? {
    var bestScore = Int.MIN_VALUE
    var move: Pair<Int, Int>? = null
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == EMPTY) {
                board[i][j] = PLAYER_O
                val score = minimax(board, 0, false, Int.MIN_VALUE, Int.MAX_VALUE)
                board[i][j] = EMPTY
                if (score > bestScore) {
                    bestScore = score
                    move = i to j
                }
            }
        }
    }
    return move
}
