package carlos.nicolau.galves.login_mvvm_c.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import carlos.nicolau.galves.login_mvpc.domain.model.login.User
import carlos.nicolau.galves.login_mvvm_c.R
import carlos.nicolau.galves.login_mvvm_c.framework.MvvmcViewModelFactory
import carlos.nicolau.galves.login_mvvm_c.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginViewModel = ViewModelProviders.of(this, MvvmcViewModelFactory)
            .get(LoginViewModel::class.java)

        loginViewModel.showLoading.observe(this, Observer {
            onLoading(it)
        })

        login.setOnClickListener {
            val user = User()
            user.email = username.text.toString()
            user.password = password.text.toString()

            loginViewModel.touchOnLogin(user)
            loginViewModel.didLogin.observe(this, Observer {
                doLogin(it)
            })
        }
    }

    fun onLoading(isLoading: Boolean) = if (isLoading) {
        loading.visibility = View.VISIBLE
    } else {
        loading.visibility = View.INVISIBLE
    }

    fun doLogin(statusLogin: Boolean) {
        if (statusLogin) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            showError()
        }
    }

    private fun showError() {
        Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show()
    }
}
