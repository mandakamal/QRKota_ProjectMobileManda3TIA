package com.qrkota.pekanbaru

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qrkota.pekanbaru.databinding.ActivityTutorialBinding

/** Materi pertemuan 9: ViewPager2 dan DotsIndicator. */
class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.tutorialViewPager.adapter = TutorialFragmentAdapter(this)
        binding.dotIndicator.attachTo(binding.tutorialViewPager)
    }
}
