package mk.learner.daggerhiltlab.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).run {
        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {}

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {}
        })
        show()
    }
}

