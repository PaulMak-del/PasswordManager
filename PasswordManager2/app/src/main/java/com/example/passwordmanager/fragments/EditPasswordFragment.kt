package com.example.passwordmanager.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.passwordmanager.databinding.FragmentEditPasswordBinding
import com.example.passwordmanager.model.Password
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [EditPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        val pass = requireArguments().getString("passwordPassword")

        binding.editTextPassword.setText(pass)
        binding.editTextPasswordName.setText(name)

        binding.ButtonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_editPasswordFragment_to_passwordListFragment)
        }

        binding.ButtonConfirm.setOnClickListener {
            val newName = binding.editTextPasswordName.text.toString()
            val newPass = binding.editTextPassword.text.toString()
            if (newName.trim().isEmpty() || newPass.trim().isEmpty()) {
                Snackbar.make(view, "Поля не должны быть пустыми", Snackbar.LENGTH_LONG).show()
            }
            else {
                viewModel.updateById(id, newName, newPass)
                findNavController().navigate(R.id.action_editPasswordFragment_to_passwordListFragment)
            }
        }

        return view
    }
}