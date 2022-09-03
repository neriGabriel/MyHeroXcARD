package com.example.myxherocard.ui.login

import android.app.Activity.RESULT_OK
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myxherocard.R
import com.example.myxherocard.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val startFireBaseAuthForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val response = IdpResponse.fromResultIntent(result.data)
        when(result.resultCode) {
            RESULT_OK -> {
                Log.d(
                    TAG, "Successfully signed as user: "+
                        "${FirebaseAuth.getInstance().currentUser?.displayName}!")
                findNavController().navigate(LoginFragmentDirections
                    .actionLoginFragmentToHomeFragment())
            }
            else -> {
                Toast.makeText(context, "Authentication failed, error:" +
                        " ${response?.error?.errorCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        binding.login.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val authenticationProviders  = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startFireBaseAuthForResult.launch(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(authenticationProviders)
            .setTheme(R.style.Theme_MyXHeroCard)
            .setLogo(R.drawable.ic_launcher_background)
            .build())

    }

    companion object {
        val TAG = LoginFragment::class.simpleName
    }

}