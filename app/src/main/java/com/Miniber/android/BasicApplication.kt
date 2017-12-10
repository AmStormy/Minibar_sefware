package com.Miniber.android

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.stetho.Stetho
import com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import timber.log.Timber.DebugTree


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

    }


    var localizationDelegate = LocalizationApplicationDelegate(this)

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

}