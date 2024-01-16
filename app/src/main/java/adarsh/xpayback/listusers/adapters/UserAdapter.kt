package adarsh.xpayback.listusers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import adarsh.core.model.User
import adarsh.xpayback.R
import adarsh.xpayback.databinding.UserItemsBinding
import adarsh.xpayback.utils.loadUrl

class UserAdapter(
    private val listener: (User) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val diffUtilCallback = object
        : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }
    }

    val userDiffer = AsyncListDiffer(this, diffUtilCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userDiffer.currentList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userDiffer.currentList.size
    }

    inner class UserViewHolder(private val binding: UserItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                userName.text = "${user.firstName} ${user.lastName}"
                if (user.image.isNullOrEmpty()){
                    userImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    userImage.setImageResource(R.drawable.no_photo)
                }else{
                    userImage.scaleType = ImageView.ScaleType.FIT_XY
                    userImage.loadUrl(user.image!!)
                }

                bloodGroup.text = "Blood Group ${user.bloodGroup}"
                userEmail.text = user.email
                userMobileNumber.text = user.phone
                "${user.address.city}, ${user.address.state}".also { userAddress.text = it }
                root.setOnClickListener {
                    listener(user)
                }
            }
        }
    }
}