package com.example.passwordmanager.fragments

import android.content.ClipboardManager
import android.content.ClipData
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordmanager.*
import com.example.passwordmanager.databinding.FragmentPasswordListBinding
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.viewModels.PasswordViewModelFactory
import com.example.passwordmanager.viewModels.PasswordsListViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PasswordListFragment : Fragment() {
    private lateinit var view: View
    private lateinit var binding: FragmentPasswordListBinding
    private lateinit var adapter: MyAdapter
    private lateinit var userID: String
    private val viewModel: PasswordsListViewModel by viewModels {
        PasswordViewModelFactory((activity?.application as App).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPasswordListBinding.inflate(layoutInflater)
        view = binding.root
        userID = Firebase.auth.currentUser?.uid ?: "NONE"

        adapter = (activity?.application as App).adapter
        Log.d("ddd", "local adapter: $adapter")

        adapter.actionListener = object : PasswordActionListener {
            override fun onEditClick(password: Password, holder: MyAdapter.ViewHolder) {
                Snackbar.make(view, "OnEditClick", Snackbar.LENGTH_LONG).show()
                val bundle = Bundle()
                bundle.putInt("layoutPosition", holder.layoutPosition)
                bundle.putInt("passwordID", password.id.toInt())
                bundle.putString("passwordName", password.name)
                bundle.putString("passwordPassword", password.password)
                bundle.putString("passwordLogin", password.login)
                findNavController().navigate(R.id.action_passwordListFragment_to_editPasswordFragment, bundle)
            }

            override fun onLeftArrowClick(elOpen: OpenElemBinding) {
                Log.d("ddd", "OnArrowClick()")
                //adapter.
                elOpen.chevronRightImageView.visibility = View.VISIBLE
                elOpen.favoriteBorderImageView.visibility = View.VISIBLE
                elOpen.deleteImageView.visibility = View.VISIBLE
                elOpen.editImageView.visibility = View.VISIBLE
            }

            override fun onRightArrowClick(elOpen: OpenElemBinding) {
                Log.d("ddd", "OnArrowClick()")
                elOpen.chevronRightImageView.visibility = View.GONE
                elOpen.favoriteBorderImageView.visibility = View.GONE
                elOpen.deleteImageView.visibility = View.GONE
                elOpen.editImageView.visibility = View.GONE
            }

            override fun onCopyClick(password: Password) {
                Log.d("ddd", "OnCopyClick()")
                val clipManager = (view.context.getSystemService(CLIPBOARD_SERVICE)) as ClipboardManager
                val clipData = ClipData.newPlainText("label", password.password)
                clipManager.setPrimaryClip(clipData)
            }

            override fun onDeleteClick(password: Password, position: Int) {
                Snackbar.make(view, "OnDeleteClick {$position}", Snackbar.LENGTH_LONG).show()
                viewModel.deletePassword(password, adapter, position)
                adapter.notifyItemRemoved(position)
            }

            override fun onFavoriteClick(
                password: Password,
                elOpen: OpenElemBinding,
            ) {
                if (password.isFavorite) {
                    elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    password.isFavorite = false
                } else {
                    elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                    password.isFavorite = true
                }
                viewModel.updatePassword(password)
            }
        }

        viewModel.getPasswords(userID, 0).observe(viewLifecycleOwner) {
            adapter.allPasswords = it
            adapter.passwords = it
        }
        viewModel.getPasswords(userID, 1).observe(viewLifecycleOwner) {
            adapter.likedPasswords = it
        }


        val recyclerView = binding.recyclerView
        val manager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        binding.addButtonView.setOnClickListener {
            findNavController().navigate(R.id.action_passwordListFragment_to_addPasswordFragment)
        }

        return view
    }
}

