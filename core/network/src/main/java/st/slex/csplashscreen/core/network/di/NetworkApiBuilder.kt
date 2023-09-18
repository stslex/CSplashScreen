package st.slex.csplashscreen.core.network.di

object NetworkApiBuilder {

    fun build(): NetworkClientApi = DaggerNetworkClientComponent.create()
}