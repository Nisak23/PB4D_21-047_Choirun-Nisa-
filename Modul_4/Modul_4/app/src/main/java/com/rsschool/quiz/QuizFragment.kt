package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var chosenOption = 0

    private var listener: QuizListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionNumber = arguments?.getInt(QUESTION_NUMBER_KEY) ?: 0
        val checkedOption = arguments?.getInt(CHECKED_NUMBER_KEY)
        val dataQuestion = arguments?.getString(DATA_QUESTION_KEY)
        val question = dataQuestion?.substringBefore('#')
        val answers = getAnswers(dataQuestion)

        binding.pertanyaan.text = question

        binding.pilihanSatu.text = answers[1]
        binding.pilihanDua.text = answers[2]
        binding.pilihanTiga.text = answers[3]
        binding.pilihanEmpat.text = answers[4]
        binding.pilihanLima.text = answers[5]

        when (checkedOption) {
            0 -> binding.btnLanjut.isEnabled = false
            1 -> binding.pilihanSatu.isChecked = true
            2 -> binding.pilihanDua.isChecked = true
            3 -> binding.pilihanTiga.isChecked = true
            4 -> binding.pilihanEmpat.isChecked = true
            5 -> binding.pilihanLima.isChecked = true
        }

        chosenOption = checkedOption ?: 0

        if (questionNumber == LAST_QUESTION_NUMBER) {
            binding.btnLanjut.text = "SELESAI"
        }

        if (questionNumber == FIRST_QUESTION_NUMBER) {
            binding.btnKembali.isEnabled = false
            binding.toolbar.navigationIcon = null
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.btnLanjut.isEnabled = true
            chosenOption = when (checkedId) {
                R.id.pilihan_satu -> 1
                R.id.pilihan_dua -> 2
                R.id.pilihan_tiga -> 3
                R.id.pilihan_empat -> 4
                R.id.pilihan_lima -> 5
                else -> 0
            }

        }

        listener = activity as? QuizListener

        binding.btnLanjut.setOnClickListener {
            listener?.next(chosenOption, questionNumber)
        }

        binding.btnKembali.setOnClickListener {
            listener?.previous(chosenOption, questionNumber)
        }

        binding.toolbar.title = "Soal ${questionNumber + 1}"
        binding.toolbar.setNavigationOnClickListener {
            listener?.previous(chosenOption, questionNumber)
        }
    }

    private fun getAnswers(dataQuestion: String?): List<String> {
        dataQuestion ?: return emptyList()
        return dataQuestion.split(DELIMITER)
    }

    interface QuizListener {

        fun next(chosenOption: Int, questionNumber: Int)

        fun previous(chosenOption: Int, questionNumber: Int)
    }

    companion object {

        private const val DATA_QUESTION_KEY = "TEXT_Q"
        private const val QUESTION_NUMBER_KEY = "QUESTION_NUMBER"
        private const val CHECKED_NUMBER_KEY = "CHECKED_NUMBER"
        private const val DELIMITER = '#'
        private const val FIRST_QUESTION_NUMBER = 0
        private const val LAST_QUESTION_NUMBER = 4

        fun newInstance(
            text: String,
            chekedOptionNumber: Int,
            questionNumber: Int
        ): QuizFragment {
            return QuizFragment().apply {
                arguments = bundleOf(
                    DATA_QUESTION_KEY to text,
                    CHECKED_NUMBER_KEY to chekedOptionNumber,
                    QUESTION_NUMBER_KEY to questionNumber
                )
            }
        }
    }
}