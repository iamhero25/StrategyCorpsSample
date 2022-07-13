package com.strategycorps.medlite.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.strategycorps.medlite.adapters.CategoryAdapter
import com.strategycorps.medlite.adapters.OnCategorySelectListener
import com.strategycorps.medlite.adapters.OnItemClickListener
import com.strategycorps.medlite.adapters.ProductListAdapter
import com.strategycorps.medlite.databinding.FragmentProductListBinding
import com.strategycorps.medlite.models.CategoryModel
import com.strategycorps.medlite.models.Product
import com.strategycorps.medlite.utils.NetworkResult
import com.strategycorps.medlite.utils.observeOnce
import com.strategycorps.medlite.viewmodel.CategoryViewModel
import com.strategycorps.medlite.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment(), OnItemClickListener, OnCategorySelectListener {

    private var _binding:  FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductListAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private val mainViewModel by viewModels<ProductViewModel>()
    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductListBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCategoryRecyclerView()
        
        setUpProductRecyclerView()

        readProductDatabase()

        readCategoryDatabase()

    }

    private fun readProductDatabase() {
        lifecycleScope.launch {
            mainViewModel.readProducts.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()){
                    productAdapter.setData(database)
                    hideShimmerEffect()
                }else{
                    requestProductApiData()
                }
            })
        }
    }

    private fun readCategoryDatabase() {
        lifecycleScope.launch{
            categoryViewModel.getLocalCategories.observeOnce(viewLifecycleOwner, { categoryDatabase ->
                if (categoryDatabase.isNotEmpty()){
                    categoryAdapter.setData(categoryDatabase)
                }else{
                    requestCategoryApiData()
                }
            })
        }
    }

    private fun requestProductApiData() {
        mainViewModel.getProducts()
        mainViewModel.productResponse.observe(viewLifecycleOwner, { response ->

            when(response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.let {
                        println(it.toString())
                        productAdapter.setData(it.data!!)
                    }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(requireContext(), "Error occurred", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading ->{
                    showShimmerEffect()
                }
            }
        })
    }

    private fun requestCategoryApiData() {
        categoryViewModel.getCategories()
        categoryViewModel.categoryResponse.observeOnce(viewLifecycleOwner, { response ->
            categoryAdapter.setData(response)

        })
    }

    private fun setUpProductRecyclerView(){
        productAdapter = ProductListAdapter(this)
        binding.rvProducts.adapter = productAdapter
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun setUpCategoryRecyclerView(){
        categoryAdapter = CategoryAdapter(this)
        binding.rvCategory.adapter = categoryAdapter
        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun loadDataFromCache(){
        lifecycleScope.launch {
            mainViewModel.readProducts.observe(viewLifecycleOwner, {database ->
                if (database.isNotEmpty()){
                    productAdapter.setData(database)
                }
            })
        }
    }

    private fun showShimmerEffect(){
        binding.llShimmer.visibility = View.VISIBLE
        binding.llBanner.visibility = View.INVISIBLE
    }

    private fun hideShimmerEffect(){
        binding.llShimmer.visibility = View.GONE
        binding.llBanner.visibility = View.VISIBLE
    }

    override fun onItemClickListener(position: Int, product: Product) {
        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(product))
    }

    override fun onCategorySelectListener(category: CategoryModel) {

        mainViewModel.getProductsByCategory(category.name!!)

        lifecycleScope.launch {
            mainViewModel.filteredProducts.observeOnce(viewLifecycleOwner, { database ->
                println(database?.toString())
                if (database.isNotEmpty()){
                    println(database.toString())
                    productAdapter.setData(database)
                    binding.llNoResult.visibility = View.GONE
                    binding.rvProducts.visibility = View.VISIBLE
                }else{
                    binding.rvProducts.visibility = View.GONE
                    binding.llNoResult.visibility = View.VISIBLE
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}