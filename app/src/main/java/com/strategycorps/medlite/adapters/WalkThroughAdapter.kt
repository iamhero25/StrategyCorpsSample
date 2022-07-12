package com.strategycorps.medlite.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.strategycorps.medlite.R
import com.strategycorps.medlite.databinding.ItemLayoutWalkthroughBinding
import com.strategycorps.medlite.models.WalkThroughModel

class WalkThroughAdapter(walkThroughList: List<WalkThroughModel>) : RecyclerView.Adapter<WalkThroughViewHolder>() {

    private val _walkThroughList = walkThroughList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WalkThroughViewHolder {
        return WalkThroughViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_walkthrough, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WalkThroughViewHolder, position: Int) {
        val currentItem = _walkThroughList[position]
        holder.bind(currentItem, position)
    }

    override fun getItemCount(): Int {
        return _walkThroughList.size
    }
}

class WalkThroughViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)   {

    fun bind(walkThroughModel: WalkThroughModel, position: Int) {
        val bind = ItemLayoutWalkthroughBinding.bind(itemView)

        bind.apply {
            details.apply {
                text = walkThroughModel.title
                background = when (position) {
                    0 -> ContextCompat.getDrawable(context, R.drawable.bg_gradient_left_cornered);
                    2 -> ContextCompat.getDrawable(context, R.drawable.bg_gradient_right_cornered);
                    else -> ContextCompat.getDrawable(context, R.drawable.bg_gradient);
                }
                image.load(walkThroughModel.image)
            }
        }
    }

}
