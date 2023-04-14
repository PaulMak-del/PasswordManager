package com.example.passwordmanager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.ItemBinding
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password
import com.google.android.material.snackbar.Snackbar

interface PasswordActionListener {
    fun onPasswordDelete(password: Password)
    fun onPasswordDetails(password: Password)
    fun onPasswordAdd(password: Password)
    fun onPasswordFavorite(password: Password)
}

//class MyAdapter(private var passwords: List<Password>,
                //private val itemClickListener: OnItemClickListener
class MyAdapter(private val actionListener: PasswordActionListener
) :
RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var passwords: List<Password> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ViewHolder(
        val binding: ItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("ddd", "MyAdapter.onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("ddd", "MyAdapter.onBindViewHolder")
        val password = passwords[position]
        with(holder.binding) {
            elClosed.passwordNameTextView.text = password.name
            if (password.isFavorite) {
                elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            elClosed.leftImage.setOnClickListener {
                Log.d("ddd", "OnArrowClick()")
                elOpen.chevronLeftImageView.visibility = View.VISIBLE
                elOpen.favoriteBorderImageView.visibility = View.VISIBLE
                elOpen.deleteImageView.visibility = View.VISIBLE
                elOpen.editImageView.visibility = View.VISIBLE
            }
            elClosed.copyImage.setOnClickListener {
                Log.d("ddd", "OnCopyClick()")
                Snackbar.make(root, "OnCopyClick", Snackbar.LENGTH_LONG).show()
                // TODO: Copy password to buffer
            }
            elOpen.chevronLeftImageView.setOnClickListener {
                Log.d("ddd", "OnArrowClick()")
                elOpen.chevronLeftImageView.visibility = View.GONE
                elOpen.favoriteBorderImageView.visibility = View.GONE
                elOpen.deleteImageView.visibility = View.GONE
                elOpen.editImageView.visibility = View.GONE
            }
            elOpen.favoriteBorderImageView.setOnClickListener {
                if (password.isFavorite) {
                    elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    password.isFavorite = false
                } else {
                    elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                    password.isFavorite = true
                }
                actionListener.onPasswordFavorite(password)
            }
            elOpen.deleteImageView.setOnClickListener {
                Snackbar.make(root, "OnDeleteClick", Snackbar.LENGTH_LONG).show()
                actionListener.onPasswordDelete(password)
            }
            elOpen.editImageView.setOnClickListener {
                Snackbar.make(root, "OnEditClick", Snackbar.LENGTH_LONG).show()
                root.findNavController().navigate(R.id.action_passwordListFragment_to_editPasswordFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("ddd", "MyAdapter.getItemCount: " + passwords.size.toString())
        return passwords.size
    }
}