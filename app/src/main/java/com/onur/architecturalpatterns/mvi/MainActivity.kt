package com.onur.architecturalpatterns.mvi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.onur.architecturalpatterns.R
import com.onur.architecturalpatterns.mvi.arch.IView
import com.onur.architecturalpatterns.mvi.main.MainIntent
import com.onur.architecturalpatterns.mvi.main.MainState
import com.onur.architecturalpatterns.mvi.main.MainViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Model - View - Intent
 *
 */

class MainActivity : AppCompatActivity(), IView<MainState> {
    private val mViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mViewModel.state.observe(this, Observer {
            render(it)
        })

        lifecycleScope.launch {
            mViewModel.intents.send(MainIntent.FetchPhotos)
        }
    }

    override fun render(state: MainState) {
        when(state) {
            is MainState.Loading -> {
                Timber.d("onurTag show progressBar ")
            }

            is MainState.ResultError -> {
                Timber.d("onurTag render snackBar errorMessage: ${state.errorMessage}")
            }

            is MainState.ResultPhotos -> {
                Timber.d("onurTag render photos: ${state.photos.size}")
            }

            MainState.Exception -> TODO()
        }
    }
}