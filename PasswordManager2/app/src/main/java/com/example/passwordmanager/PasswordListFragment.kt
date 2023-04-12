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
import com.example.passwordmanager.databinding.OpenElemBinding
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.model.PasswordService
import kotlin.Deprecated as Deprecated1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordListFragment : Fragment(), MyAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var view: View
    private val passwordService = PasswordService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        Log.d("ddd", "PasswordListFragment.onCreate()")
        view = inflater.inflate(R.layout.fragment_password_list, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val manager: LinearLayoutManager = LinearLayoutManager(view.context)
        val password: Password = Password(22, "name22", "password22", false)

        passwordService.addPassword(password)
        val adapter = MyAdapter(passwordService.getPasswords(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        // TODO: Add a ViewModel or smt that can share data with fragments
        view.findViewById<Button>(R.id.addButtonView).setOnClickListener {
            findNavController().navigate(R.id.action_passwordListFragment_to_addPasswordFragment)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PasswordListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PasswordListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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
        TODO("copy password into the buffer")
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
        TODO("smt")
    }

    override fun onDeleteClick(password: Password) {
        TODO("smt")
    }
}