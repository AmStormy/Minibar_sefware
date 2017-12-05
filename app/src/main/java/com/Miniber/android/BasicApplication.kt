package com.Miniber.android

import android.app.Application
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder
import io.fabric.sdk.android.Fabric
import com.facebook.stetho.dumpapp.DumperPlugin
import com.facebook.stetho.DumperPluginsProvider
import timber.log.Timber.DebugTree
import timber.log.Timber
import com.crashlytics.android.core.CrashlyticsCore
import com.Miniber.android.helper.CrashlyticsTree
import com.Miniber.android.helper.InitSetting











/**
* Created by chaiwut on 29/11/17.
*/
class BasicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initial Fabric
        Fabric.with(this, Crashlytics.Builder().core(CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build()).build())

        //Log with Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
//        Timber.plant(CrashlyticsTree())

        //Initial Stetho
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp {
                    Stetho.DefaultDumperPluginsBuilder(this)
//                            .provide(MyDumperPlugin())
                            .finish()
                }
                .enableWebKitInspector{
                    Stetho.DefaultInspectorModulesBuilder(this)
                            .runtimeRepl(JsRuntimeReplFactoryBuilder(this)
                                    .addVariable("appVersion","1.0.0")
                                    .build()
                            ).finish()
                }
                .build())

        initFirstSetting()
    }

    private fun initFirstSetting() {
        InitSetting.init(this)
                .ifFirstRunApplication() // important
                .persistString("setting_value_1", "setting_value_1")
                .persistString("setting_value_2", "setting_value_2")
                .persistString("setting_value_3", "setting_value_3")
    }

}