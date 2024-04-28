package com.onur.architecturalpatterns.mvp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.onur.architecturalpatterns.R
import com.onur.architecturalpatterns.mvp.data.Photo
import com.onur.architecturalpatterns.mvp.main.IMainView
import com.onur.architecturalpatterns.mvp.main.MainInteractor
import com.onur.architecturalpatterns.mvp.main.MainPresenter
import timber.log.Timber


/**
 * Model - View - Presenter
 *
 * The MVP pattern allows separating the presentation layer from the logic so that
 * everything about how the UI works is agnostic from how we represent it on screen.
 *
 * The first thing to clarify is that MVP is not an architecture by itself, it’s only responsible for the presentation layer.
 *
 *
 * Why Use MVP?
 *
 * In Android, we have a problem arising from the fact that Android activities are closely coupled to both UI and data access mechanisms.
 *
 * For an application to be easily extensible and maintainable, we need to define well-separated layers.
 *
 * We divide the application into at least three different layers, which lets us test them independently.
 * With MVP we take most of the logic out from the activities so that we can test it without using instrumentation tests.
 *
 *
 *
 * MVP has some risks, and the most important one is that the presenter is attached to the view forever.
 * And the view is an activity, which means that:
 *
 * We can leak the activity with long-running tasks
 * We can try to update activities that have already died
 *
 */

class MainActivity : AppCompatActivity(), IMainView {

    private val presenter = MainPresenter(this, MainInteractor())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.fetchPhotos()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onPhotosFetched(photos: Array<Photo>) {
        Timber.d("onurTag mvp render photos: ${photos.size}")
    }

    override fun showProgress() {
        Timber.d("onurTag mvp show progressBar")
    }

    override fun hideProgress() {
        Timber.d("onurTag mvp hide progressBar")
    }

    override fun showErrorMessage(message: String) {
        Timber.d("onurTag mvp render snackBar errorMessage: $message")
    }
}