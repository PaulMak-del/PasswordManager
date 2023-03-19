package com.example.passwordmanager

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.ItemBinding
import com.example.passwordmanager.model.Password

class MyAdapter(private var passwords: List<Password>,
                private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onArrowClick(password: Password, iv: ImageView)
        fun onCopyClick(password: Password, iv: ImageView)
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
            el.passwordNameTextView.text = password.name
            el.leftImage.setOnClickListener {
                itemClickListener.onArrowClick(password, el.leftImage)
            }
            el.copyImage.setOnClickListener {
                itemClickListener.onCopyClick(password, el.copyImage)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("ddd", "MyAdapter.getItemCount: " + passwords.size.toString())
        return passwords.size
    }
}