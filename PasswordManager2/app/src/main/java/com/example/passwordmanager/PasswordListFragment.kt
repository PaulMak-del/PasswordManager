package com.example.passwordmanager

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.FragmentPasswordListBinding
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.model.PasswordService
import com.google.android.material.snackbar.Snackbar
import kotlin.Deprecated as Deprecated1

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordListFragment : Fragment(), MyAdapter.OnItemClickListener {
    private lateinit var view: View
    private val passwordService = PasswordService()
    private lateinit var binding: FragmentPasswordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d("ddd", "PasswordListFragment.onCreate()")

        binding = FragmentPasswordListBinding.inflate(layoutInflater)
        view = binding.root

        val recyclerView = binding.recyclerView
        val manager: LinearLayoutManager = LinearLayoutManager(view.context)
        val password: Password = Password(22, "name22", "password22", false)

        passwordService.addPassword(password)
        val adapter = MyAdapter(passwordService.getPasswords(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        // TODO: Add a ViewModel or smt that can share data with fragments
        binding.addButtonView.setOnClickListener {
            findNavController().navigate(R.id.action_passwordListFragment_to_addPasswordFragment)
        }

        return view
    }

    override fun onArrowLeftClick(password: Password, iv: OpenElemBinding) {
        Log.d("ddd", "OnArrowClick()")
        iv.chevronLeftImageView.visibility = View.VISIBLE
        iv.favoriteBorderImageView.visibility = View.VISIBLE
        iv.deleteImageView.visibility = View.VISIBLE
        iv.editImageView.visibility = View.VISIBLE
    }

    override fun onCopyClick(password: Password, iv: ImageView) {
        Log.d("ddd", "OnCopyClick()")
        Snackbar.make(view, "OnCopyClick", Snackbar.LENGTH_LONG).show()
        // TODO: Copy password to buffer
    }

    override fun onArrowRightClick(password: Password, iv: OpenElemBinding) {
        Log.d("ddd", "OnArrowClick()")
        iv.chevronLeftImageView.visibility = View.GONE
        iv.favoriteBorderImageView.visibility = View.GONE
        iv.deleteImageView.visibility = View.GONE
        iv.editImageView.visibility = View.GONE
    }

    override fun onFavoriteClick(password: Password, iv: ImageView) {
        if (password.isFavorite) {
            iv.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            password.isFavorite = false
        } else {
            iv.setImageResource(R.drawable.ic_baseline_favorite_24)
            password.isFavorite = true
        }
    }

    override fun onEditCLick(password: Password) {
        Snackbar.make(view, "OnEditClick", Snackbar.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_passwordListFragment_to_editPasswordFragment)
        // TODO: Put some data
    }

    override fun onDeleteClick(password: Password) {
        Snackbar.make(view, "OnDeleteClick", Snackbar.LENGTH_LONG).show()
        // TODO: smt
    }
}