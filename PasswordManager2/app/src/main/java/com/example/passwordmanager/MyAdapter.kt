package com.example.passwordmanager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.ItemBinding
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password

interface PasswordActionListener {
    fun onPasswordAdd(password: Password)
    fun onEditClick(password: Password)
    fun onLeftArrowClick(elOpen: OpenElemBinding)
    fun onRightArrowClick(elOpen: OpenElemBinding)
    fun onCopyClick(password: Password)
    fun onDeleteClick(password: Password)
    fun onFavoriteClick(password: Password, elOpen: OpenElemBinding)

}

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
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(password: Password, clickListener: PasswordActionListener) {
            with(binding) {
                elClosed.passwordNameTextView.text = password.name
                if (password.isFavorite) {
                    elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    elOpen.favoriteBorderImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }

                elClosed.leftImage.setOnClickListener {
                    clickListener.onLeftArrowClick(elOpen)
                }
                elOpen.editImageView.setOnClickListener {
                    clickListener.onEditClick(password)
                }
                elClosed.copyImage.setOnClickListener {
                    clickListener.onCopyClick(password)
                }
                elOpen.chevronRightImageView.setOnClickListener {
                    clickListener.onRightArrowClick(elOpen)
                }
                elOpen.favoriteBorderImageView.setOnClickListener {
                    clickListener.onFavoriteClick(password, elOpen)
                }
                elOpen.deleteImageView.setOnClickListener {
                    clickListener.onDeleteClick(password)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("ddd", "MyAdapter.onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("ddd", "MyAdapter.onBindViewHolder")
        val password = passwords[position]
        holder.bind(password, actionListener)
    }

    override fun getItemCount(): Int {
        Log.d("ddd", "MyAdapter.getItemCount: " + passwords.size.toString())
        return passwords.size
    }
}