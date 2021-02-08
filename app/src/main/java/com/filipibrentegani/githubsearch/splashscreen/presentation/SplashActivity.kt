package com.filipibrentegani.githubsearch.splashscreen.presentation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.filipibrentegani.githubsearch.findrepositoriesscreen.presentation.FindRepositoriesActivity
import com.filipibrentegani.githubsearch.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO adicionar condição para acessibilidade (não animar em caso de acessibilidade)
        binding.tvAppName.animate().alpha(1f).setDuration(1500)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    startActivity(Intent(this@SplashActivity, FindRepositoriesActivity::class.java))
                    finish()
                }
            })
    }
}