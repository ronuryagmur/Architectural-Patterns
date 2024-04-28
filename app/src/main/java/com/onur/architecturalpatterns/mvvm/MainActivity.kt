package com.onur.architecturalpatterns.mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.onur.architecturalpatterns.R
import com.onur.architecturalpatterns.mvvm.main.MainViewModel
import timber.log.Timber

/**
 * Model - View - ViewModel
 *
 * In MVP, the presenter communicates with the view through an interface.
 * In MVVM, the ViewModel communicates with the view using the Observer pattern.
 *
 * ViewModel and View communicates with observable components since they are not attached with each other
 *
 * The most important feature is that you can forget about the activity being destroyed, so you can detach from its lifecycle and do your work anytime.
 * Thanks to the ViewModel and LiveData, you don’t need to worry when the activity recreates or when it’s destroyed.
 *
 */

class MainActivity : AppCompatActivity() {
    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mViewModel.photoList.observe(this, Observer {
            Timber.d("onurTag mvvm render photos: ${it.size}")
        })

        mViewModel.dataLoading.observe(this, Observer {
            if (it) {
                Timber.d("onurTag mvvm show progressBar")
            } else {
                Timber.d("onurTag mvvm hide progressBar")
            }
        })

        mViewModel.showSnackbar.observe(this, Observer {
            Timber.d("onurTag mvvm render snackBar errorMessage: $it")
        })
    }
}