package com.example.fruitlens.ui.quiz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.fruitlens.R
import com.example.fruitlens.databinding.ActivityQuizBinding
import com.example.fruitlens.ui.quiz.data.Constants
import com.example.fruitlens.ui.quiz.data.Question

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var mQuestionList: ArrayList<Question>
    private var mSelectedPosition: Int = 0
    private var mCorrectAnswer: Int = 0
    var mCurrrentPosition: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQuestionList = Constants.getQuestion().apply {
            shuffle()
        }
        for (question in mQuestionList) {
            question.shuffleOptions()
        }

        binding.apply {
            tvOptionOne.setOnClickListener(this@QuizActivity)
            tvOptionTwo.setOnClickListener(this@QuizActivity)
            tvOptionThree.setOnClickListener(this@QuizActivity)
            btnSubmit.setOnClickListener(this@QuizActivity)
        }
        setQuestion()
    }

    private fun setQuestion() {
        val question: Question = mQuestionList[mCurrrentPosition - 1]
        val shuffledOptions = question.shuffledOptions

        binding.apply {
            tvQuestion.text = question.question
            imageView2.setImageResource(question.image)
            tvOptionOne.text = shuffledOptions[0]
            tvOptionTwo.text = shuffledOptions[1]
            tvOptionThree.text = shuffledOptions[2]
            pb.progress = mCurrrentPosition
            "$mCurrrentPosition / ${binding.pb.max}".also { tvProgress.text = it }

            defaultAppearance()
        }

        if (mCurrrentPosition == mQuestionList.size) {
            binding.btnSubmit.text = getString(R.string.finished)
        } else {
            binding.btnSubmit.text = getString(R.string.btn_submit)
        }

        mCorrectAnswer = question.newCorrectAnswer
    }

    private fun answerView(selectedPosition: Int, drawableView: Int) {
        when (selectedPosition) {
            1 -> binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 -> binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 -> binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedPosition: Int) {
        defaultAppearance()
        mSelectedPosition = selectedPosition

        tv.setTextColor(ContextCompat.getColor(this, R.color.bold_gray))
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun defaultAppearance() {
        val options = listOf(binding.tvOptionOne, binding.tvOptionTwo, binding.tvOptionThree)

        for (option in options) {
            option.setTextColor(ContextCompat.getColor(this, R.color.gray))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOptionOne -> selectedOptionView(binding.tvOptionOne, 1)
            R.id.tvOptionTwo -> selectedOptionView(binding.tvOptionTwo, 2)
            R.id.tvOptionThree -> selectedOptionView(binding.tvOptionThree, 3)
            R.id.btnSubmit -> {
                if (mSelectedPosition == 0) {
                    mCurrrentPosition++
                    when {
                        mCurrrentPosition <= 10 -> setQuestion()
                        else -> Log.d("QuizActivity", "hasil = $mCorrectAnswer")
                    }
                } else {
                    val question = mQuestionList[mCurrrentPosition - 1]
                    if (question.newCorrectAnswer != mSelectedPosition) {
                        answerView(mSelectedPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswer++
                    }
                    answerView(question.newCorrectAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrrentPosition == mQuestionList.size) {
                        binding.btnSubmit.text = getString(R.string.finished)
                    } else {
                        binding.btnSubmit.text = getString(R.string.next_question)
                    }
                    mSelectedPosition = 0
                }
            }
        }
    }
}
