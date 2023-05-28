package com.example.passwordmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordmanager.databinding.ItemBinding
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password

interface PasswordActionListener {
    fun onEditClick(password: Password, holder: MyAdapter.ViewHolder)
    fun onLeftArrowClick(elOpen: OpenElemBinding)
    fun onRightArrowClick(elOpen: OpenElemBinding)
    fun onCopyClick(password: Password)
    fun onDeleteClick(password: Password, position: Int)
    fun onFavoriteClick(password: Password, elOpen: OpenElemBinding)
}

class MyAdapter(
    //private val actionListener: PasswordActionListener,
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    lateinit var actionListener: PasswordActionListener
    /*
    Отслеживает первое изменение поля паролей, для корректного отображения списка recyclerView
    на первом запуске
     */
    private var onLaunchUpdate: Boolean = false
    var allPasswords: List<Password> = emptyList()
        set(value) {
            field = value
            if (!onLaunchUpdate) {
                notifyDataSetChanged()
                onLaunchUpdate = true
            }
        }
    var likedPasswords: List<Password> = emptyList()
        set(value) {
            field = value
            if (!onLaunchUpdate) {
                notifyDataSetChanged()
                onLaunchUpdate = true
            }
        }
    var passwords: List<Password> = emptyList()
        set(value) {
            field = value
            if (!onLaunchUpdate) {
                notifyDataSetChanged()
                onLaunchUpdate = true
            }
            //notifyDataSetChanged()
        }

    class ViewHolder(
        private val binding: ItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(holder: ViewHolder, password: Password, clickListener: PasswordActionListener) {
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
                    clickListener.onEditClick(password, holder)
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
                    clickListener.onDeleteClick(password, bindingAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val password = passwords[position]
        holder.bind(holder, password, actionListener)
    }

    override fun getItemCount(): Int {
        return passwords.size
    }
}