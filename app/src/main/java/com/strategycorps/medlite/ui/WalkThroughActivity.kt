package com.strategycorps.medlite.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.strategycorps.medlite.R
import com.strategycorps.medlite.adapters.WalkThroughAdapter
import com.strategycorps.medlite.databinding.ActivityWalkThroughBinding
import com.strategycorps.medlite.models.WalkThroughModel

private lateinit var viewPager2: ViewPager2

class WalkThroughActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWalkThroughBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walk_through)

        binding = ActivityWalkThroughBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager2 = findViewById(R.id.walkViewpager)
        viewPager2.adapter = WalkThroughAdapter(getWalkThroughList())

        initActions()

    }


    private fun initActions(){

        binding.signIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.llJoinTeam.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getWalkThroughList(): List<WalkThroughModel> {
        return listOf(
            WalkThroughModel("Pharmacy at your finger tips\nNo more lines", R.drawable.walk_through_one),
            WalkThroughModel("Wide selection of pharmaceutical items\nto choose from  ", R.drawable.walk_through_two),
            WalkThroughModel("Order now for convenient \nand safe delivery ", R.drawable.walk_through_three),
        )
    }
}