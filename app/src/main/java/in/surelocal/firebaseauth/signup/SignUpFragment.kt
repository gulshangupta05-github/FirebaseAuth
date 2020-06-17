package `in`.surelocal.firebaseauth.signup

import `in`.surelocal.firebaseauth.MainActivity
import `in`.surelocal.firebaseauth.R
import `in`.surelocal.firebaseauth.SignUpActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.*
import kotlin.math.log


class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var auth: FirebaseAuth
    var TAG = "MainActivity"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
        auth = FirebaseAuth.getInstance()
        signup.setOnClickListener() {

            val name = et_name.text.toString()
            val mobile = et_mobile.text.toString()
            val email = et_emailsignup.text.toString()
            val password = et_signuppassword.text.toString()
            val confirm = et_confirmPassword.text.toString()

            if (name.isEmpty()) {
                et_name.error = "Name Empty"
                et_name.requestFocus()
            } else if (mobile.isEmpty()) {
                et_mobile.error = "Mobile no Empty"
                et_mobile.requestFocus()
            } else if (email.isEmpty()) {
                et_emailsignup.error = "Email Empty"
                et_emailsignup.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_emailsignup.error = "invailid email"
                et_emailsignup.requestFocus()
            } else if (password.isEmpty()) {
                et_signuppassword.error = "Password Empty"
                et_signuppassword.requestFocus()
            } else if (confirm.isEmpty()) {
                et_confirmPassword.error = "Confirm Password Empty"
                et_confirmPassword.requestFocus()
            } else if (password != confirm) {
                Toast.makeText(context, "password not match", Toast.LENGTH_SHORT).show()
            } else {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "hello")
                            //  val  user=auth.currentUser
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            activity?.finish()
                        } else {
//                        Toast.makeText(context, "Authentication failed.",Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "Authentication failed")
                        }

                    }
                    .addOnFailureListener() { task ->
                        Log.d(TAG, "$task")

                    }

            }
        }
    }
}


