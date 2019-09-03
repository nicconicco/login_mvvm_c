package carlos.nicolau.galves.login_mvvm_c.presentation.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import carlos.nicolau.galves.login_mvpc.domain.model.login.GetUserResponse
import carlos.nicolau.galves.login_mvpc.domain.model.login.User
import carlos.nicolau.galves.login_mvpc.mvp.provider.SchedulerProvider
import carlos.nicolau.galves.login_mvvm_c.framework.Interactors
import carlos.nicolau.galves.login_mvvm_c.framework.MvvmcViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable


class LoginViewModel(application: Application, interactors: Interactors, scheduler: SchedulerProvider) : MvvmcViewModel
    (application, interactors, scheduler) {

    val didLogin = MutableLiveData<Boolean>()
    val showLoading = MutableLiveData<Boolean>()

    fun touchOnLogin(user: User) {
        interactors.getUser(user)
            .subscribeOn(scheduler.io())
            .retry(1)
            .observeOn(scheduler.ui())
            .subscribe(object : SingleObserver<GetUserResponse> {
                override fun onSuccess(getUserResponse: GetUserResponse) {
                    didLogin.postValue(true)
                    showLoading.postValue(false)
                }

                override fun onSubscribe(d: Disposable) {
                    showLoading.postValue(true)
                }

                override fun onError(e: Throwable) {
                    showLoading.postValue(false)
                }
            })
    }
}