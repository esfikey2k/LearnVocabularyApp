package com.example.learnvocabularyapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.learnvocabularyapp.databinding.FragmentDetailBinding
import com.example.learnvocabularyapp.model.WordsModel
import com.example.learnvocabularyapp.room.ModelDB
import com.example.learnvocabularyapp.room.WordsDAO
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var wordTr: String? = null
    private var wordEn: String? = null
    private var wordEs: String? = null
    private var wordGe: String? = null
    private var sentenceTr: String? = null
    private var sentenceEs: String? = null
    private var sentenceEn: String? = null
    private var sentenceGe: String? = null
    private var wordImageUrl: String? = null
    private var wordId: String? = null
    private var wordLanguage: String? = null
    private lateinit var wordsDao: WordsDAO
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        try {

            viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

            val wordsDb = ModelDB.getInstance(requireContext())
            wordsDao= wordsDb.wordsDao

            viewModel.isAddedToLearned.observe(viewLifecycleOwner) { isAdded ->
                if (isAdded) {
                    binding.addToLearnedButton.visibility = View.GONE
                    binding.removeToLearnedButton.visibility = View.VISIBLE
                } else {
                    binding.addToLearnedButton.visibility = View.VISIBLE
                    binding.removeToLearnedButton.visibility = View.GONE
                }
            }

            arguments?.let { bundle ->
                wordTr = bundle.getString("tr")
                wordEn = bundle.getString("en")
                wordEs = bundle.getString("es")
                wordGe = bundle.getString("ge")
                sentenceTr = bundle.getString("sentenceTr")
                sentenceEs = bundle.getString("sentenceEs")
                sentenceEn = bundle.getString("sentenceEn")
                sentenceGe = bundle.getString("sentenceGe")
                wordImageUrl = bundle.getString("wordImageUrl")
                wordId = bundle.getInt("id").toString()
                wordLanguage = bundle.getString("language")

                viewLifecycleOwner.lifecycleScope.launch {
                    val isItemInDatabase = withContext(Dispatchers.IO) {
                        wordsDao.getById(wordId!!.toInt()).isNotEmpty()
                    }
                    viewModel.setIsAddedToLearned(isItemInDatabase)
                }
            }

            setFragmentResultListener("learnedWord"){_,bundle ->
                val learnedId = bundle.getInt("learnedId").toString()
                val learnedTr = bundle.getString("learnedTr")
                val learnedEn = bundle.getString("learnedEn")
                val learnedEs = bundle.getString("learnedEs")
                val learnedGe = bundle.getString("learnedGe")
                val learnedSentenceTr = bundle.getString("learnedSentenceTr")
                val learnedSentenceEs = bundle.getString("learnedSentenceEs")
                val learnedSentenceEn = bundle.getString("learnedSentenceEn")
                val learnedSentenceGe = bundle.getString("learnedSentenceGe")
                val learnedWordImageUrl = bundle.getString("learnedWordImageUrl")

                val learnedModel= WordsModel(learnedId.toInt(),learnedEn!!,learnedEs!!,learnedGe!!,learnedTr!!,learnedSentenceEn!!,learnedSentenceEs!!,learnedSentenceGe!!,learnedSentenceTr!!,learnedWordImageUrl!!)

                Picasso.get().load(learnedWordImageUrl).into(binding.ivDetail)
                binding.tvWordForLanguage.text = learnedTr
                binding.tvWordForTurkish.text = learnedEn
                binding.tvWarning.text= "Kelimenin ingilizcede karşılığını görmek için resme dokunun!"
                binding.tvForSentenceLanguage.text= learnedSentenceEn
                binding.tvForTurkishLanguage.text= learnedSentenceTr

                binding.addToLearnedButton.visibility = View.GONE
                binding.removeToLearnedButton.visibility = View.VISIBLE
                viewModel.removeFromLearned()

                viewModel.setIsAddedToLearned(true)

                binding.cardViewDetail.setOnClickListener {
                    flipAnimation()
                }

                binding.removeToLearnedButton.setOnClickListener {
                    binding.addToLearnedButton.visibility = View.VISIBLE
                    binding.removeToLearnedButton.visibility = View.GONE
                    viewModel.removeFromLearned()
                    wordsDao.delete(learnedModel)
                }

            }

            val isItemInDatabase = wordsDao.getById(wordId!!.toInt()).isNotEmpty()
            viewModel.setIsAddedToLearned(isItemInDatabase)


            binding.tvWordForTurkish.visibility= TextView.VISIBLE
            binding.tvWordForLanguage.visibility= TextView.GONE

            Picasso.get().load(wordImageUrl).into(binding.ivDetail)

            binding.cardViewDetail.setOnClickListener {
                flipAnimation()
            }

            when (wordLanguage) {
                "en" -> {
                    binding.tvWordForLanguage.text = wordEn
                    binding.tvWordForTurkish.text = wordTr
                    binding.tvWarning.text= "Kelimenin ingilizcede karşılığını görmek için resme dokunun!"
                    binding.tvForSentenceLanguage.text= sentenceEn
                    binding.tvForTurkishLanguage.text= sentenceTr
                }
                "es" -> {
                    binding.tvWordForLanguage.text = wordEs
                    binding.tvWordForTurkish.text = wordTr
                    binding.tvWarning.text= "Kelimenin ispanyolca karşılığını görmek için resme dokunun!"
                    binding.tvForSentenceLanguage.text= sentenceEs
                    binding.tvForTurkishLanguage.text= sentenceTr
                }
                "ge" -> {
                    binding.tvWordForLanguage.text = wordGe
                    binding.tvWordForTurkish.text = wordTr
                    binding.tvWarning.text = "Kelimenin almanca karşılığını görmek için resme dokunun!"
                    binding.tvForSentenceLanguage.text= sentenceGe
                    binding.tvForTurkishLanguage.text= sentenceTr
                }
                null -> {
                    view.findNavController().navigate(R.id.action_wordsFragment2_to_choiceFragment)

                }

            }
            val model= WordsModel(wordId!!.toInt(),wordEn!!,wordEs!!,wordGe!!,wordTr!!,sentenceEn!!,sentenceEs!!,sentenceGe!!,sentenceTr!!,wordImageUrl!!)

            binding.addToLearnedButton.setOnClickListener {
                binding.addToLearnedButton.visibility = View.GONE
                binding.removeToLearnedButton.visibility = View.VISIBLE
                viewModel.addToLearned()
                wordsDao.insert(model)
                            }

            binding.removeToLearnedButton.setOnClickListener {
                binding.addToLearnedButton.visibility = View.VISIBLE
                binding.removeToLearnedButton.visibility = View.GONE
                viewModel.removeFromLearned()
                wordsDao.delete(model)

            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        return view
    }

    private fun flipAnimation() {

        val oa1 = ObjectAnimator.ofFloat(binding.cardViewDetail, "scaleX", 1f, 0f).apply {
            interpolator = DecelerateInterpolator()
            duration = 300
        }

        val oa2 = ObjectAnimator.ofFloat(binding.cardViewDetail, "scaleX", 0f, 1f).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = 300
        }

        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)

                if (binding.tvWordForTurkish.visibility == TextView.VISIBLE) {
                    binding.tvWordForTurkish.visibility = TextView.GONE
                    binding.tvWordForLanguage.visibility = TextView.VISIBLE
                } else {
                    binding.tvWordForLanguage.visibility = TextView.GONE
                    binding.tvWordForTurkish.visibility = TextView.VISIBLE
                }

                oa2.start()
            }
        })

        oa1.start()
    }


}