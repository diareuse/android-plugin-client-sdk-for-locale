package com.twofortyfouram.locale.sdk.client.internal

import android.app.Activity
import android.content.Intent
import com.twofortyfouram.locale.sdk.client.TaskerIntent
import com.twofortyfouram.locale.sdk.client.ui.activity.IPluginActivity
import net.jcip.annotations.Immutable
import com.twofortyfouram.locale.api.Intent as ApiIntent

@Immutable
class TaskerPluginActivityDelegate<T> :
    AbstractPluginActivityDelegate<T>() where T : Activity, T : IPluginActivity {

    override fun isLocalePluginIntent(intent: Intent): Boolean {
        val action = intent.action

        val supportedIntents = listOf(
            ApiIntent.ACTION_EDIT_CONDITION,
            ApiIntent.ACTION_EDIT_SETTING,
            TaskerIntent.ACTION_EDIT_EVENT
        )
        return supportedIntents.any { it == action }
    }

}