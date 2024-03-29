/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        val randomNonRepeatingNumber = generateRandomNonRepeatingNumber(digitsToGuess)
        println("Please enter $digitsToGuess-digit a number:")
        while (true) {
            val number = readlnOrNull()?.toIntOrNull()
            if (number != null) {
                val result = checkUserInputAgainstGeneratedNumber(number, randomNonRepeatingNumber)
                println("Output: ${result.m}:${result.n}")
                if (number == randomNonRepeatingNumber) {
                    printWinAsciiArt()
                    println("Congratulations! You guessed the correct number.")
                    break
                }
            } else {
                println("Invalid input. Please enter a number.")
            }
        }
    }

    private fun printWinAsciiArt() {
        println("""
                            
                                                                     
                             _   _  ___  _   _  __      _____  _ __  
                            | | | |/ _ \| | | | \ \ /\ / / _ \| '_ \ 
                            | |_| | (_) | |_| |  \ V  V / (_) | | | |
                             \__, |\___/ \__,_|   \_/\_/ \___/|_| |_|
                             |___/                                   
                             
                        """.trimIndent())
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length > 9 || length < 1) {
            throw IllegalArgumentException("The length is more than 9 or less than 1.")
        } else {
            val numbers = mutableListOf<Int>()
            for (i in 1..9) {
                numbers.add(i)
            }
            numbers.shuffle()
            var result = ""
            for (i in 0 until length) {
                result += numbers[i]
            }
            result.toInt()
        }
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `m`: The number of digits guessed correctly (regardless of their position).
     *         2. `n`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val inputStr = input.toString()
        val generatedStr = generatedNumber.toString()

        if (inputStr.length != generatedStr.length) {
            throw IllegalArgumentException("The inputs do not have the same number of digits.")
        }

        val uniqueDigitsFromInput = inputStr.toSet()
        val m = uniqueDigitsFromInput.count { it in generatedStr }
        val n = inputStr.indices.count { inputStr[it] == generatedStr[it] }

        CompareResult(m, n)
    }
}

fun main() {
    printAppTitleAsciiArt()

    println("Welcome to the Number Guessing Game!")
    val app = App()

    println("Enter a number of digits to guess (between 1 and 9, or press Enter to use the default):")
    val userInput = readlnOrNull()

    if (userInput.isNullOrEmpty()) app.playNumberGame() else app.playNumberGame(userInput.toInt())

}

private fun printAppTitleAsciiArt() {
    println("""
        
             __                 _                   ___                     _                 ___                     
          /\ \ \_   _ _ __ ___ | |__   ___ _ __    / _ \_   _  ___  ___ ___(_)_ __   __ _    / _ \__ _ _ __ ___   ___ 
         /  \/ / | | | '_ ` _ \| '_ \ / _ \ '__|  / /_\/ | | |/ _ \/ __/ __| | '_ \ / _` |  / /_\/ _` | '_ ` _ \ / _ \
        / /\  /| |_| | | | | | | |_) |  __/ |    / /_\\| |_| |  __/\__ \__ \ | | | | (_| | / /_\\ (_| | | | | | |  __/
        \_\ \/  \__,_|_| |_| |_|_.__/ \___|_|    \____/ \__,_|\___||___/___/_|_| |_|\__, | \____/\__,_|_| |_| |_|\___|
                                                                                    |___/                             
    """.trimIndent())
}