package com.example.passwordmanager.fragments

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.passwordmanager.App
import com.example.passwordmanager.viewModels.PasswordViewModelFactory
import com.example.passwordmanager.viewModels.PasswordsListViewModel
import com.example.passwordmanager.R
import com.example.passwordmanager.databinding.FragmentAddPasswordBinding
import com.example.passwordmanager.model.Password
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Math.random

class AddPasswordFragment : Fragment() {
    private lateinit var binding: FragmentAddPasswordBinding
    private lateinit var userID: String

    private val viewModel: PasswordsListViewModel by viewModels {
        PasswordViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        userID = Firebase.auth.currentUser!!.uid

        binding.ButtonConfirm.setOnClickListener {
            Snackbar.make(binding.root, "Add password", Snackbar.LENGTH_SHORT).show()
            val name: String = binding.editTextPasswordName.text.toString()
            val pass: String = binding.editTextPassword.text.toString()
            val login: String = binding.editTextLogin.text.toString()

            if (name.trim().isEmpty() || pass.trim().isEmpty() || login.trim().isEmpty()) {
                Snackbar.make(view, "Поля не должны быть пустыми", Snackbar.LENGTH_LONG).show()
            } else {
                val password = Password(name, login, pass, userID)
                viewModel.insertPassword(password)

                findNavController().navigate(R.id.action_addPasswordFragment_to_passwordListFragment)
            }
        }

        binding.openEyeImage.setOnClickListener {
            binding.editTextPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.openEyeImage.visibility = View.GONE
            binding.closeEyeImage.visibility = View.VISIBLE
        }

        binding.closeEyeImage.setOnClickListener {
            binding.editTextPassword.inputType = (InputType.TYPE_CLASS_TEXT or(InputType.TYPE_TEXT_VARIATION_PASSWORD))
            binding.openEyeImage.visibility = View.VISIBLE
            binding.closeEyeImage.visibility = View.GONE
        }

        binding.ButtonGenerate.setOnClickListener {
            val length = (random() * 8 + 7).toInt()
            val password = CharArray(length) {
                listOf('a'..'z', 'A'..'Z', '0'..'9').random().random()
            }
            binding.editTextPassword.setText(password.joinToString(""))
        }

        return view
    }
}