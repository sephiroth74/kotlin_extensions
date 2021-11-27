package it.sephiroth.android.library.kotlin_extensions.os.build

import android.os.Build

fun isAPI(value: Int) = Build.VERSION.SDK_INT == value

fun isAtLeastAPI(value: Int) = Build.VERSION.SDK_INT >= value
