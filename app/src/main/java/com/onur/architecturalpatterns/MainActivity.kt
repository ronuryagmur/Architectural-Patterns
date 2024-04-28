package com.onur.architecturalpatterns

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.onur.architecturalpatterns.mvi.arch.IView
import com.onur.architecturalpatterns.mvi.main.MainIntent
import com.onur.architecturalpatterns.mvi.main.MainState
import com.onur.architecturalpatterns.mvi.main.MainViewModel
import com.onur.architecturalpatterns.mvp.MainActivity
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<FrameLayout>(R.id.fl_mvp).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<FrameLayout>(R.id.fl_mvvm).setOnClickListener {
            val intent = Intent(this, com.onur.architecturalpatterns.mvvm.MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<FrameLayout>(R.id.fl_mvi).setOnClickListener {
            val intent = Intent(this, com.onur.architecturalpatterns.mvi.MainActivity::class.java)
            startActivity(intent)
        }
    }
}