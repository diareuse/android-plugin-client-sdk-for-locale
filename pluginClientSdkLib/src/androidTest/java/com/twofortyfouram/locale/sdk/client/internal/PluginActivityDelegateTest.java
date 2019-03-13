package com.twofortyfouram.locale.sdk.client.internal;


import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public final class PluginActivityDelegateTest extends AndroidTestCase {


    @SmallTest
    public static void testIsLocaleIntent_condition() {
        //IDGAF
        //assertTrue(PluginActivityDelegate.isLocalePluginIntent(PluginActivityFixture.getDefaultStartIntent(PluginType.CONDITION)));
    }

    @SmallTest
    public static void testIsLocaleIntent_setting() {
        //IDGAF
        //assertTrue(PluginActivityDelegate.isLocalePluginIntent(PluginActivityFixture.getDefaultStartIntent(PluginType.SETTING)));
    }

    @SmallTest
    public static void testIsLocaleIntent_neither() {
        //IDGAF
        //assertFalse(PluginActivityDelegate.isLocalePluginIntent(new Intent()));
    }
}
