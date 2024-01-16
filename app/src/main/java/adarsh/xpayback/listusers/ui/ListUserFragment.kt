package adarsh.xpayback.listusers.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import adarsh.core.data.response.UserResponse
import adarsh.core.model.User
import adarsh.xpayback.R
import adarsh.xpayback.databinding.FragmentListUserBinding
import adarsh.xpayback.listusers.adapters.UserAdapter
import adarsh.xpayback.listusers.viewmodel.ListUserViewModel
import androidx.navigation.fragment.findNavController

class ListUserFragment : Fragment() {

    private val viewModel: ListUserViewModel by viewModel()

    //Fragment Binding
    private var _binding: FragmentListUserBinding? = null

    private val binding get() = _binding

    private lateinit var adapter: UserAdapter

    private lateinit var users: List<User>

    private var mContext: Context? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserList()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mContext==null){
            return
        }
        val listener : (User) -> Unit = {
            binding?.etSearch?.text?.clear()
            val bundle = Bundle()
            bundle.putInt("userId", it.id)
            findNavController().navigate(R.id.action_listProductFragment_to_userDetailsFragment, bundle)
        }
        viewModel.usersList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is UserResponse.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }

                is UserResponse.Success -> {
                    users = response.data.users
                    adapter = UserAdapter(listener)
                    adapter.userDiffer.submitList(users)
                    binding?.rvUsers?.adapter = adapter
                    binding?.progressBar?.visibility = View.GONE
                }

                is UserResponse.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    binding?.message?.text = response.message
                    binding?.rvUsers?.visibility = View.GONE

                }
            }
        }

        binding?.etSearch?.addTextChangedListener {
            val searchString = it.toString()
            filterProductList(searchString)
        }
    }

    private fun filterProductList(searchString: String) {
        val filteredList = users.filter { product ->
            product.firstName.lowercase().contains(searchString.lowercase())
        }
        adapter.userDiffer.submitList(filteredList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

}
