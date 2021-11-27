package it.sephiroth.android.library.kotlin_extensions.content.res.resources.theme

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat


fun Theme.resolveAttribute(@AttrRes id: Int, resolveRefs: Boolean = false): Int {
    val typedValue = TypedValue()
    resolveAttribute(id, typedValue, resolveRefs)
    return typedValue.data
}

fun Theme.getTypedValue(@AttrRes id: Int, resolveRefs: Boolean = false): TypedValue {
    val typedValue = TypedValue()
    resolveAttribute(id, typedValue, resolveRefs)
    return typedValue
}

fun Theme.getDimensionPixelSize(context: Context, @AttrRes id: Int): Int {
    return resources.getDimensionPixelSize(context.theme.resolveAttribute(id))
}

fun Theme.getInteger(context: Context, @AttrRes id: Int): Int {
    return context.resources.getInteger(context.theme.resolveAttribute(id))
}

fun Theme.getColorStateList(context: Context, @AttrRes id: Int): ColorStateList? {
    return ContextCompat.getColorStateList(context, context.theme.resolveAttribute(id))
}

fun Theme.getColor(context: Context, @AttrRes id: Int): Int {
    return ContextCompat.getColor(context, context.theme.resolveAttribute(id))
}
