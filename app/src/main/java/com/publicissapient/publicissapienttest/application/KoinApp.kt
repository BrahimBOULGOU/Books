package com.publicissapient.publicissapienttest.application

import android.app.Application
import com.publicissapient.publicissapienttest.models.repository.BooksModule
import com.publicissapient.publicissapienttest.netwroks.networkModule
import com.publicissapient.publicissapienttest.ui.viewFragment
import com.publicissapient.publicissapienttest.viewmodels.listBooksViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(listOf(viewFragment, networkModule,
                BooksModule, listBooksViewModelModule
            ))
        }
    }
}