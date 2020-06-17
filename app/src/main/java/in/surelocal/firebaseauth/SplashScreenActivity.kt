package `in`.surelocal.firebaseauth

import `in`.surelocal.firebaseauth.splash.SplashFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if (savedInstanceState==null){
        supportFragmentManager.beginTransaction().replace(
            R.id.container,SplashFragment.newInstance()
        ).commit()
    }

}}

