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
import com.example.passwordmanager.databinding.FragmentEditPasswordBinding
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

        binding.ButtonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_editPasswordFragment_to_passwordListFragment)
        }

        binding.ButtonConfirm.setOnClickListener {
            Snackbar.make(binding.root, "Confirm button", Snackbar.LENGTH_LONG).show()
            //viewModel.update(password)
        }

        return view
    }
}