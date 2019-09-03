package carlos.nicolau.galves.login_mvvm_c.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import carlos.nicolau.galves.login_mvpc.mvp.provider.SchedulerProvider

object MvvmcViewModelFactory : ViewModelProvider.Factory {

    lateinit var scheduler: SchedulerProvider
    lateinit var application: Application
    lateinit var dependencies: Interactors

    fun inject(application: Application, dependencies: Interactors, scheduler: SchedulerProvider) {
        MvvmcViewModelFactory.application = application
        MvvmcViewModelFactory.dependencies = dependencies
        MvvmcViewModelFactory.scheduler = scheduler
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MvvmcViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                Application::class.java,
                Interactors::class.java,
                SchedulerProvider::class.java
            )
                .newInstance(
                    application,
                    dependencies,
                    scheduler
                )
        } else {
            throw IllegalStateException("ViewModel must extend MvvmcViewModel")
        }
    }

}