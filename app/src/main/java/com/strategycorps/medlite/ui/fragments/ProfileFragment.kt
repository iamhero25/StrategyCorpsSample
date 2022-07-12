package com.strategycorps.medlite.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.strategycorps.medlite.R
import com.strategycorps.medlite.adapters.SkillAdapter
import com.strategycorps.medlite.databinding.FragmentProductListBinding
import com.strategycorps.medlite.databinding.FragmentProfileBinding
import com.strategycorps.medlite.models.Skill

class ProfileFragment : Fragment() {

    private var _binding:  FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var skillAdapter: SkillAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        skillAdapter = SkillAdapter(getSkillList())
        binding.rvSkillList.adapter = skillAdapter
        binding.rvSkillList.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun getSkillList(): List<Skill> {

        return listOf(
            Skill("Java"),
            Skill("Kotlin"),
            Skill("JavaScript"),
            Skill("MVVM"),
            Skill("OOP"),
            Skill("Reactjs"),
            Skill("Nextjs"),
            Skill("Firebase"),
            Skill("and more")
        )

    }

}