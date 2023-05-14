package com.rsschool.quiz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), QuizFragment.QuizListener, ResultFragment.ResultListener {

    private val data: List<String> = listOf(
        "Apa itu Data Mining?#Proses menggali data mentah#Metode untuk menjual data#Teknik membuat salinan data#Proses menemukan pola tersembunyi dalam data#Metode untuk mengamankan data",
        "Apa yang dimaksud dengan Preprocessing dalam Data Mining?#Menghapus data yang tidak relevan# Menggabungkan beberapa data menjadi satu#Mencari data yang hilang atau kosong#Mengubah format data menjadi lebih mudah diproses#Memperkecil ukuran data",
        "Apa yang dimaksud dengan Algoritma K-Means?#Algoritma untuk mencari data terkecil#Algoritma untuk mengelompokkan data ke dalam beberapa kelompok#Algoritma untuk mengurutkan data secara menaik#Algoritma untuk mencari data terbesar#Algoritma untuk menghapus data duplikat",
        "Apa itu Association Rule dalam Data Mining?#Aturan untuk menghubungkan data dengan data lainnya#Aturan untuk mencari data yang sering muncul bersama# Aturan untuk menghapus data yang tidak relevan#Aturan untuk memisahkan data menjadi beberapa kelompok#Aturan untuk mengubah format data menjadi lebih mudah dipahami",
        "Apa yang dimaksud dengan Classification dalam Data Mining?#Proses mengurutkan data berdasarkan nilai tertentu#Proses menggabungkan beberapa data menjadi satu#Proses mencari data yang sering muncul bersama#Proses memisahkan data menjadi beberapa kelompok#Proses memprediksi dan mengklasifikasikan data ke dalam kategori tertentu"
    )

    private val jawaban_benar: List<Int> = listOf(
        4, 4, 2, 2, 5
    )

    private val chosenOptions = mutableListOf<Int>(0, 0, 0, 0, 0)
    private var currentQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openQuizFragment(data[0], chosenOptions[0], currentQuestion)
    }

    private fun openQuizFragment(dataQuestions: String, checkedOption: Int, questionNumber: Int) {
        val firstFragment = QuizFragment.newInstance(dataQuestions, checkedOption, questionNumber)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment)
        transaction.commit()
    }

    private fun openResultFragment(result: String, description: String) {
        val secondFragment = ResultFragment.newInstance(result, description)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
        transaction.commit()
    }

    override fun back() {
        currentQuestion = 0
        chosenOptions.fill(0)
        openQuizFragment(data[currentQuestion], chosenOptions[currentQuestion], currentQuestion)
    }

    override fun next(chosenOption: Int, questionNumber: Int) {
        Log.i("TAG", "chose = $chosenOption  -> $chosenOptions")
        chosenOptions[questionNumber] = chosenOption
        currentQuestion = questionNumber + 1
        setTheme(getThemeId(currentQuestion))
        window.statusBarColor = resources.getColor(getColorId(currentQuestion))
        if (questionNumber == data.size - 1) {
            openResultFragment(generateResult(), generateDescription())
        } else {
            openQuizFragment(data[currentQuestion], chosenOptions[currentQuestion], currentQuestion)
        }
    }

    override fun previous(chosenOption: Int, questionNumber: Int) {
        Log.i("TAG", "chose = $chosenOption  -> $chosenOptions")
        chosenOptions[questionNumber] = chosenOption
        currentQuestion = questionNumber - 1
        setTheme(getThemeId(currentQuestion))
        window.statusBarColor = resources.getColor(getColorId(currentQuestion))
        openQuizFragment(data[currentQuestion], chosenOptions[currentQuestion], currentQuestion)
    }

    private fun generateResult(): String {
        var counter = 0;
        chosenOptions.forEachIndexed { index, i ->
            if (i == jawaban_benar[index]) {
                counter++
            }
        }
        val percent: Long = counter.toLong()  * 100 / data.size.toLong()
        return "Nilai: $percent "
    }

    private fun generateDescription(): String {
        val description = StringBuilder()
        description.append(generateResult() + "\n\n")
        chosenOptions.forEachIndexed { index, i ->
            val question = data[index].substringBefore('#')
            val answer = data[index].split('#')[i]
            description.append("${index+1}) $question\nYour answer: $answer \n\n")
        }
        return description.toString()
    }

    private fun getThemeId(questionNumber: Int): Int {
        return when (questionNumber) {
            0 -> R.style.Theme_Quiz_Utama
            1 -> R.style.Theme_Quiz_Utama
            2 -> R.style.Theme_Quiz_Utama
            3 -> R.style.Theme_Quiz_Utama
            4 -> R.style.Theme_Quiz_Utama
            else -> R.style.Theme_Quiz_Utama
        }
    }

    private fun getColorId(questionNumber: Int): Int {
        return when (questionNumber) {
            0 -> R.color.deep_orange_100_dark
            1 -> R.color.deep_orange_100_dark
            2 -> R.color.deep_orange_100_dark
            3 -> R.color.deep_orange_100_dark
            4 -> R.color.deep_orange_100_dark
            else -> R.color.deep_orange_100_dark
        }
    }
}