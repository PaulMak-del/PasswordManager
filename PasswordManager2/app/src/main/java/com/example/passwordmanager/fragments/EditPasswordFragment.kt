package com.example.passwordmanager.fragments

import android.os.Bundle
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
import com.example.passwordmanager.databinding.FragmentEditPasswordBinding
import com.google.android.material.snackbar.Snackbar

class EditPasswordFragment : Fragment() {
    private lateinit var binding: FragmentEditPasswordBinding

    private val viewModel: PasswordsListViewModel by viewModels {
        PasswordViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        val id = requireArguments().getInt("passwordID")
        val name = requireArguments().getString("passwordName")
        val login = requireArguments().getString("passwordLogin")
        val pass = requireArguments().getString("passwordPassword")

        binding.editTextPassword.setText(pass)
        binding.editTextPasswordName.setText(name)
        binding.editTextLogin.setText(login)

        binding.ButtonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_editPasswordFragment_to_passwordListFragment)
        }

        binding.ButtonConfirm.setOnClickListener {
            val newName = binding.editTextPasswordName.text.toString()
            val newLogin = binding.editTextLogin.text.toString()
            val newPass = binding.editTextPassword.text.toString()
            if (newName.trim().isEmpty() || newPass.trim().isEmpty() || newLogin.trim().isEmpty()) {
                Snackbar.make(view, "Поля не должны быть пустыми", Snackbar.LENGTH_LONG).show()
            }
            else {
                viewModel.updatePasswordById(id, newName, newLogin, newPass)
                findNavController().navigate(R.id.action_editPasswordFragment_to_passwordListFragment)
            }
        }

        return view
    }
}