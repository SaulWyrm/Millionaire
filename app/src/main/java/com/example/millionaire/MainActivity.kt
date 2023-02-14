package com.example.millionaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.* // импортируем библиотеку KoltinX со сгенерированными определениями ресурсов (2ой способ; устаревший)
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {


    //так как переменные создаются на этапе открытия проги, нам нужна поздняя инициализация виджетов
    // и здесь мы просто обьявляем (1ый способ; устаревший)
/*    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button*/

    private val rounds = mutableListOf<Round>()
    private var currentRound = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // инициализация не требуется, используется KotlinX/Kotlin Android Extensions
        tvQuestion.text = "Тут будет первый вопрос"

        // инициализация переменных виджетов
       /* button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)*/

//---
        fillRounds()
        updateUI()
    }

    // заполняет банк вопросов данными
    private fun fillRounds() {

        rounds.run {
            add(Round("Что такое Луна?", "Звезда", "Планета",
            "Спутник", "Круг сыра", 3, 100))
            add(Round("В каком году запущен первый спутник?", "1957", "1961",
            "1965", "1969", 1, 1_000))
            add(Round("Сколько спутников у Марса?", "0", "1",
            "2", "4", 3, 10_000))
            add(Round("Как называется спутник Плутона?", "Нет спутников", "Харон",
            "Энцелад", "Ио", 2, 100_000))
            add(Round("Какой крупнейший спутник у Юпитера?", "Европа", "Каллисто",
            "Титан", "Ганнимед", 4, 1_000_000))
        }
    }

    // обновляет вопрос на экране
    private fun updateUI() {
        tvQuestion.text = rounds[currentRound].question

        button1.text = rounds[currentRound].answer1
        button2.text = rounds[currentRound].answer2
        button3.text = rounds[currentRound].answer3
        button4.text = rounds[currentRound].answer4
    }

    // проверяет выбор пользователя
    private fun checkAnswer(givenId: Int) =
        (givenId == rounds[currentRound].rightId)

    // определяет конец игры
    private fun goNextRound() : Boolean {
        if (currentRound >= rounds.size - 1) return false

        currentRound++
        updateUI()
        return true
    }

    // определяет поведение при победе или поражении
    private fun processRound(givenId: Int) {
        if (checkAnswer(givenId)) {
            if (!goNextRound()) {
                Toast.makeText(this, getString(R.string.wintext), Toast.LENGTH_SHORT).show()
                finish()
            }
            Toast.makeText(this, "хорошая работа", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.losetext), Toast.LENGTH_SHORT).show()

            finish()
        }
    }

    fun buttonClick(view: View) {
        try {
            val id = view.tag.toString().toInt()
            processRound(id)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


}