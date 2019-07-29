package it.sephiroth.android.library.kotlin_extensions.app

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build

inline val Activity.isInMultiWindow: Boolean
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isInMultiWindowMode
        } else {
            false
        }
    }

fun Context.isNightMode() =
    (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES