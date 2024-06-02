package com.example.fruitlens.ui.quiz.data

data class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val correctAnswer: Int
) {
    var shuffledOptions: List<String> = listOf(optionOne, optionTwo, optionThree)
    var newCorrectAnswer: Int = correctAnswer

    fun shuffleOptions() {
        val options = shuffledOptions.toMutableList()
        options.shuffle()
        shuffledOptions = options

        // Update new correct answer position
        newCorrectAnswer = options.indexOf(
            when (correctAnswer) {
                1 -> optionOne
                2 -> optionTwo
                3 -> optionThree
                else -> optionOne
            }
        ) + 1
    }
}