package `in`.surelocal.firebaseauth

import `in`.surelocal.firebaseauth.login.LoginFragment
import `in`.surelocal.firebaseauth.signup.SignUpFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportFragmentManager.beginTransaction().replace(
            R.id.container, SignUpFragment.newInstance()
        ).commit()
    }
}
