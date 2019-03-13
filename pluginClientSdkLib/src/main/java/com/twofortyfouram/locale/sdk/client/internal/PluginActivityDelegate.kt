package com.twofortyfouram.locale.sdk.client.internal

import android.app.Activity
import android.content.Intent
import com.twofortyfouram.locale.sdk.client.ui.activity.IPluginActivity
import net.jcip.annotations.Immutable
import com.twofortyfouram.locale.api.Intent as ApiIntent

/**
 * Activities that implement the [IPluginActivity] interface can delegate much of their
 * responsibility to this class.
 *
 * @param <T> Plug-in activity.
</T> */
/*
 * This class is intended to make the implementation of various plug-in Activities DRY.
 *
 * This class has no state, so therefore is immutable.
 */
@Immutable
class PluginActivityDelegate<T> :
    AbstractPluginActivityDelegate<T>() where T : Activity, T : IPluginActivity {

    override fun isLocalePluginIntent(intent: Intent): Boolean {
        val action = intent.action

        val supportedIntents = listOf(
            ApiIntent.ACTION_EDIT_CONDITION,
            ApiIntent.ACTION_EDIT_SETTING
        )
        return supportedIntents.any { it == action }
    }

}
