package com.example.passwordmanager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.ItemBinding
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password

class MyAdapter(private var passwords: List<Password>,
                private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onArrowLeftClick(password: Password, iv: OpenElemBinding)
        fun onCopyClick(password: Password, iv: ImageView)
        fun onArrowRightClick(password: Password, iv: OpenElemBinding)
        fun onFavoriteClick(password: Password, iv: ImageView)
        fun onEditCLick(password: Password)
        fun onDeleteClick(password: Password)
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
            elClosed.leftImage.setOnClickListener {
                itemClickListener.onArrowLeftClick(password, elOpen)
            }
            elClosed.copyImage.setOnClickListener {
                itemClickListener.onCopyClick(password, elClosed.copyImage)
            }
            elOpen.chevronLeftImageView.setOnClickListener {
                itemClickListener.onArrowRightClick(password, elOpen)
            }
            elOpen.favoriteBorderImageView.setOnClickListener {
                itemClickListener.onFavoriteClick(password, elOpen.favoriteBorderImageView)
            }
            elOpen.deleteImageView.setOnClickListener {
                itemClickListener.onDeleteClick(password)
            }
            elOpen.editImageView.setOnClickListener {
                itemClickListener.onEditCLick(password)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("ddd", "MyAdapter.getItemCount: " + passwords.size.toString())
        return passwords.size
    }
}