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
import com.example.learnvocabularyapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var wordTr: String? = null
    private var wordEn: String? = null
    private var wordEs: String? = null
    private var wordImageUrl: String? = null
    private var wordId: String? = null
    private var wordLanguage: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let { bundle ->
            wordTr = bundle.getString("tr")
            wordEn = bundle.getString("en")
            wordEs = bundle.getString("es")
            wordImageUrl = bundle.getString("wordImageUrl")
            wordId = bundle.getString("id")
            wordLanguage = bundle.getString("language")
        }

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
            }
            "es" -> {
                binding.tvWordForLanguage.text = wordEs
                binding.tvWordForTurkish.text = wordTr
                binding.tvWarning.text= "Kelimenin ispanyolca karşılığını görmek için resme dokunun!"
            }
            "de" -> {
                binding.tvWordForLanguage.text = wordTr
                binding.tvWordForTurkish.text = wordEn
                binding.tvWarning.text = "Kelimenin almanca karşılığını görmek için resme dokunun!"
            }

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