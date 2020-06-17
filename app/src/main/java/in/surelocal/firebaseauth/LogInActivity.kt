package `in`.surelocal.firebaseauth

import `in`.surelocal.firebaseauth.login.LoginFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportFragmentManager.beginTransaction().replace(
            R.id.container,LoginFragment.newInstance()
        ).commit()
    }
}
