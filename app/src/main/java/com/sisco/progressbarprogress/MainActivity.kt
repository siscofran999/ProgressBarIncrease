package com.sisco.progressbarprogress

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progress = findViewById<ProgressBar>(R.id.progress)
        val txvProgress = findViewById<TextView>(R.id.txvProgress)
        val img = findViewById<AppCompatImageView>(R.id.img)

        val animator = ObjectAnimator.ofInt(progress, "progress", 1, 100)
        animator.apply {
            duration = 2000
            addUpdateListener { value ->
                txvProgress.text = String.format(
                    getString(R.string.label_n_percent),
                    value.animatedValue.toString()
                )
                if (value.animatedValue == 100) {
                    img.invisibleWithFadeOutAnimation()
                    progress.visibility = View.GONE
                    txvProgress.visibility = View.GONE
                    value.cancel()
                }
            }
            start()
        }
    }

    private fun View.invisibleWithFadeOutAnimation() {
        this.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_out))
    }
}