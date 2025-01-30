class TicTacToe {
    private val board = Array(3) { Array(3) { ' ' } }
    private var currentPlayer = 'X'

    fun play() {
        var winner: Char? = null
        while (winner == null && !isBoardFull()) {
            printBoard()
            playerMove()
            winner = checkWinner()
            switchPlayer()
        }
        printBoard()
        println(if (winner != null) "El ganador es $winner" else "Es un empate!")
    }

    private fun printBoard() {
        println("\n  0 1 2")
        for (i in board.indices) {
            print("$i ")
            println(board[i].joinToString("|"))
            if (i < 2) println("  -----")
        }
    }

    private fun playerMove() {
        var validMove = false
        while (!validMove) {
            println("Jugador $currentPlayer, ingrese su movimiento (fila y columna): ")
            val input = readlnOrNull()?.split(" ")?.mapNotNull { it.toIntOrNull() }
            if (input != null && input.size == 2) {
                val (row, col) = input
                if (row in 0..2 && col in 0..2 && board[row][col] == ' ') {
                    board[row][col] = currentPlayer
                    validMove = true
                } else {
                    println("Movimiento inválido. Intente de nuevo.")
                }
            } else {
                println("Entrada inválida. Intente de nuevo.")
            }
        }
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }

    private fun checkWinner(): Char? {
        for (i in 0..2) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return board[i][0]
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return board[0][i]
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return board[0][0]
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return board[0][2]
        return null
    }

    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it != ' ' } }
    }
}

fun main() {
    val game = TicTacToe()
    game.play()
}
