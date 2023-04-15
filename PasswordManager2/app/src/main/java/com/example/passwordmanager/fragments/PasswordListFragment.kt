package com.example.passwordmanager.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordmanager.*
import com.example.passwordmanager.databinding.FragmentPasswordListBinding
import com.example.passwordmanager.model.Password
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordListFragment : Fragment() {
    private lateinit var view: View
    private lateinit var binding: FragmentPasswordListBinding
    private lateinit var adapter: MyAdapter

    /*
    private val viewModel: PasswordsListViewModel by viewModels {
        PasswordViewModelFactory((activity?.application as App).repository)
    }
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d("ddd", "PasswordListFragment.onCreate()")

        binding = FragmentPasswordListBinding.inflate(layoutInflater)
        view = binding.root

        val viewModel: PasswordsListViewModel by viewModels {
            PasswordViewModelFactory((activity?.application as App).repository)
        }

        adapter = MyAdapter(object : PasswordActionListener {
            override fun onPasswordDelete(password: Password) {
                viewModel.delete(password)
            }

            override fun onPasswordAdd(password: Password) {
                viewModel.insert(password)
            }

            override fun onPasswordFavorite(password: Password) {
                viewModel.update(password)
            }

            override fun onEditClick(password: Password) {
                Snackbar.make(view, "OnEditClick", Snackbar.LENGTH_LONG).show()
                val bundle = Bundle()
                bundle.putInt("passwordID", password.id.toInt())
                bundle.putString("passwordName", password.name)
                bundle.putString("passwordPassword", password.password)
                findNavController().navigate(R.id.action_passwordListFragment_to_editPasswordFragment, bundle)
            }
        })


        viewModel.allPasswords.observe(viewLifecycleOwner, Observer {
            adapter.passwords = it
        })

        val recyclerView = binding.recyclerView
        val manager: LinearLayoutManager = LinearLayoutManager(view.context)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        binding.addButtonView.setOnClickListener {
            findNavController().navigate(R.id.action_passwordListFragment_to_addPasswordFragment)
        }

        return view
    }
}

