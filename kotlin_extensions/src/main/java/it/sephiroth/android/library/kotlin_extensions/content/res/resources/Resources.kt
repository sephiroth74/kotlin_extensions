package it.sephiroth.android.library.kotlin_extensions.content.res.resources

import android.content.res.Configuration
import android.content.res.Resources


/**
 * kotlin_extensions
 *
 * @author Alessandro Crugnola on 27.11.21 - 13:25
 */

inline val Resources.isPortrait: Boolean get() = this.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
