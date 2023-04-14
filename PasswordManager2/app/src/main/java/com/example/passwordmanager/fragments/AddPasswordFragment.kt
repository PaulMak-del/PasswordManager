package com.example.passwordmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.passwordmanager.App
import com.example.passwordmanager.PasswordViewModelFactory
import com.example.passwordmanager.PasswordsListViewModel
import com.example.passwordmanager.R
import com.example.passwordmanager.databinding.FragmentAddPasswordBinding
import com.example.passwordmanager.model.Password
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [AddPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPasswordFragment : Fragment() {
    private lateinit var binding: FragmentAddPasswordBinding

    private val viewModel: PasswordsListViewModel by viewModels {
        PasswordViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        binding.ButtonConfirm.setOnClickListener {
            Snackbar.make(binding.root, "Add password", Snackbar.LENGTH_SHORT).show()
            val name: String = binding.editTextPasswordName.text.toString()
            val pass: String = binding.editTextPassword.text.toString()

            val password: Password = Password(name, pass)
            viewModel.insert(password)

            findNavController().navigate(R.id.action_addPasswordFragment_to_passwordListFragment)
        }

        // Inflate the layout for this fragment
        return view
    }
}