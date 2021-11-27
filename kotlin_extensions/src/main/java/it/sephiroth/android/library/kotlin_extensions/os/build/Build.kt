package it.sephiroth.android.library.kotlin_extensions.os.build

import android.os.Build

fun Build.isAPI(value: Int) = Build.VERSION.SDK_INT == value

fun Build.isAtLeastAPI(value: Int) = Build.VERSION.SDK_INT >= value
