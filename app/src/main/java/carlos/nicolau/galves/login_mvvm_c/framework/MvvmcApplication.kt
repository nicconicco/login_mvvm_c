package carlos.nicolau.galves.login_mvvm_c.framework

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import carlos.nicolau.galves.core.data.GetUserRepository
import carlos.nicolau.galves.core.interactors.GetUser
import carlos.nicolau.galves.login_mvpc.mvp.provider.AppSchedulerProvider
import carlos.nicolau.galves.login_mvvm_c.framework.service.RetrofitGetUserDataSource

class MvvmcApplication : Application() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun getAppContext(): Context? {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        val getUserRepository = GetUserRepository(
            RetrofitGetUserDataSource(
            )
        )

        MvvmcViewModelFactory.inject(
            this,
            Interactors(
                GetUser(getUserRepository)
            ),
            AppSchedulerProvider()
        )
    }
}