package com.strategycorps.medlite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.strategycorps.medlite.databinding.ItemChipTagBinding
import com.strategycorps.medlite.models.Skill

class SkillAdapter(private val skillList: List<Skill>): RecyclerView.Adapter<SkillAdapter.SkillViewHolder>() {

    class SkillViewHolder(private val binding: ItemChipTagBinding): RecyclerView.ViewHolder(binding.root) {

        companion object{
            fun from(parent: ViewGroup): SkillAdapter.SkillViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChipTagBinding.inflate(layoutInflater, parent, false)
                return SkillAdapter.SkillViewHolder(binding)
            }
        }

        fun bind(skill: Skill){
            binding.apply {
                tvChip.text = skill.name
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        return SkillViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        val currentSkill = skillList[position]
        holder.bind(currentSkill)
    }

    override fun getItemCount(): Int {
       return skillList.size
    }


}