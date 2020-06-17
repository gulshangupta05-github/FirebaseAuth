package `in`.surelocal.firebaseauth.login

import `in`.surelocal.firebaseauth.MainActivity
import `in`.surelocal.firebaseauth.R
import `in`.surelocal.firebaseauth.SignUpActivity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*




class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
     private lateinit var mauth: FirebaseAuth
    var TAG="LOGIN"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        mauth=FirebaseAuth.getInstance()

        login.setOnClickListener() {
            val email = et_username.text.toString()
            val password = et_password.text.toString()
            if (email.isEmpty()){
                et_username.error="Username Empty"
                et_username.requestFocus()
            }else if (password.isEmpty())
            {
                et_password.error="Password Empty"
                et_password.requestFocus()
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_username.error="Invailid UserName"
                et_username.requestFocus()
            }
            else{
                mauth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(){task ->
                        if (task.isSuccessful){
                            Log.d(TAG,"login successful")
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            //startActivity(intent)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            activity?.finish()
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Log.d(TAG,"Log in Failed")
                            Toast.makeText(context, "Email id Not Match", Toast.LENGTH_SHORT).show()

                        }
                    }
//                val intent = Intent(requireContext(), MainActivity::class.java)
//                //startActivity(intent)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent)
//                activity?.finish()
//                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

            }
        }
        txt_create.setOnClickListener(){
            val intent=Intent(requireContext(),SignUpActivity::class.java)
            startActivity(intent)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity?.finish()

        }
    }

}





