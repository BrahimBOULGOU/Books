package com.publicissapient.publicissapienttest.application

import android.app.Application
import com.publicissapient.publicissapienttest.models.repository.BooksRepository
import com.publicissapient.publicissapienttest.models.repository.OfferRepository
import com.publicissapient.publicissapienttest.netwroks.networkModule
import com.publicissapient.publicissapienttest.ui.fragments.BasketFragment
import com.publicissapient.publicissapienttest.ui.fragments.ListBooksFragment
import com.publicissapient.publicissapienttest.viewmodels.BasketViewModel
import com.publicissapient.publicissapienttest.viewmodels.ListBooksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

val viewFragmentModule = module {
    factory { ListBooksFragment() }
    factory { BasketFragment() }
}

val RepositorysModule = module {
    factory { BooksRepository(get(), get()) }
    factory { OfferRepository(get(), get()) }
}

val ViewModelModule = module {
    factory { ListBooksViewModel(get()) }
    factory { BasketViewModel(get()) }
}

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(
                listOf(
                        viewFragmentModule, networkModule,
                        RepositorysModule, ViewModelModule
                )
            )
        }
    }
}