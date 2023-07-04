package st.slex.feature_collection.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.stslex.csplashscreen.core.core.AppModule
import st.slex.feature_collection.domain.SingleCollectionInteractor
import st.slex.feature_collection.domain.SingleCollectionInteractorImpl
import st.slex.feature_collection.ui.SingleCollectionViewModel

class SingleCollectionModule : AppModule {

    override fun invoke() = module {
        factoryOf(::SingleCollectionInteractorImpl) { bind<SingleCollectionInteractor>() }
        viewModelOf(::SingleCollectionViewModel)
    }
}