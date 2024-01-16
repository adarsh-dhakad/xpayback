package adarsh.xpayback.userdeatils.ui

import adarsh.core.data.response.UserResponse
import adarsh.core.model.User
import adarsh.xpayback.databinding.FragmentUserDetailsBinding
import adarsh.xpayback.userdeatils.viewmodel.UserDetailsViewModel
import adarsh.xpayback.utils.loadUrl
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsFragment: Fragment(){
    private val viewModel: UserDetailsViewModel by viewModel()
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private var mContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = arguments?.getInt("userId")?:0
        viewModel.getUserDetails(userId)
        viewModel.userDetailsResponse.observe(viewLifecycleOwner) {
            Log.d("UserDetailsFragment", "onViewCreated: ${it}")
            it?.let { response ->
                when (response) {
                    is UserResponse.Success -> {
                        binding.apply {
                            progressBar.isGone = true
                        }
                        setUpUi(response.data)
                    }
                    is UserResponse.Error -> {
                        binding.apply {
                            val error = response.message
                            progressBar.isGone = true
                            progressText.text = error
                            Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show()
                        }
                    }
                    is UserResponse.Loading -> {
                        binding.apply {
                            progressBar.isGone = false
                        }
                    }
                }
            }

        }
    }

    fun setUpUi(user: User){
        binding.apply {
            ivUserImage.loadUrl(user.image)
            name.setText("${user.firstName} ${user.lastName}")
            userEmail.setText(user.email)
            userPhoneNumEditText.setText(user.phone)
            companyName.text = user.company.name
            companyTitle.text = user.company.title
            companyDepartment.text = user.company.department
            tvAddress.text = user.company.address.address
            tvCity.text = user.company.address.city
            state.text = user.company.address.state
            pincode.text = user.company.address.postalCode
            userName.text = user.username
            password.text = user.password
            coin.text = user.crypto.coin
            wallet.text = user.crypto.wallet
            network.text = user.crypto.network
            gender.text = user.gender
            birthDate.text = user.birthDate
            bloodGroup.text = user.bloodGroup



            Log.e("UserDetailsFragment", "setUpUi: ${user}" )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


}