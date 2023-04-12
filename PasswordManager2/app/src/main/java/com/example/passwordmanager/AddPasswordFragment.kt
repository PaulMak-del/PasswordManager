package com.example.passwordmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.passwordmanager.databinding.ActivityMainBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPasswordBinding.inflate(layoutInflater)

        binding.ButtonConfirm.setOnClickListener {
            Snackbar.make(binding.root, "Add password", Snackbar.LENGTH_SHORT).show()
            val name: String = binding.editTextPasswordName.toString()
            val pass: String = binding.editTextPassword.toString()
            // TODO: Make id not repeatable
            val id: Long = 12

            // TODO: Somehow add pass to DB, I guess
            val password: Password = Password(id, name, pass, false)

            findNavController().navigate(R.id.action_addPasswordFragment_to_passwordListFragment)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_password, container, false)
    }
}