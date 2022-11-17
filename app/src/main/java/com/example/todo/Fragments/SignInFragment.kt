package com.example.todo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todo.R
import com.example.todo.databinding.FragmentSignInBinding
import com.example.todo.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSignInBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }

    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents()  {
        binding.textViewSignIn.setOnClickListener{
            navControl.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.nextBtn.setOnClickListener {
            val email = binding.emailET.text.toString().trim()
            val pass = binding.passET.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty() ) {
               binding.progressBar2.visibility=View.VISIBLE
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Login Successfully!", Toast.LENGTH_SHORT)
                                .show()
                            navControl.navigate(R.id.action_signInFragment_to_homeFragment)

                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        binding.progressBar2.visibility=View.GONE
                    }
                }else{
                Toast.makeText(context, "Empty Fields Not Allowed", Toast.LENGTH_SHORT)
                    .show()
                }

        }
    }

}