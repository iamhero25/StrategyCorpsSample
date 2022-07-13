package com.strategycorps.medlite.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.strategycorps.medlite.R
import com.strategycorps.medlite.databinding.FragmentProductDetailsBinding
import com.strategycorps.medlite.models.Product

class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var product: Product
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            updateUi()
    }

    private fun updateUi(){

        product = args.productDetails

        binding.apply {

            tvProductName.text = product.title
            tvProductDesc.text = product.description
            tvProductUnit.text = product.unit

            if (product.price == "$0"){
                tvProductPrice.text = product.originalPrice
                llOldPrice.isVisible = false
            } else {
                llOldPrice.isVisible = true
                tvOldPrice.text = product.originalPrice
                tvProductPrice.text = product.price
                tvProductPrice.setTextColor(Color.parseColor("#0288D1"))
            }

            ivProductImage.load(product.image){
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.no_image)
            }

            ivBack.setOnClickListener {
                goBack()
            }
        }
    }

    private fun goBack(){
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}