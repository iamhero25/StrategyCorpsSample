package com.strategycorps.medlite.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.strategycorps.medlite.data.entities.ProductEntity
import com.strategycorps.medlite.repository.Repository
import com.strategycorps.medlite.utils.NetworkResult
import com.strategycorps.medlite.utils.NetworkState.Companion.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    //ROOM DATABASE
    val readProducts: LiveData<List<ProductEntity>> = repository.local.readProducts().asLiveData()

    private var _filteredProducts: MutableLiveData<List<ProductEntity>> = MutableLiveData()
    val filteredProducts: LiveData<List<ProductEntity>> get() = _filteredProducts


    fun getProductsByCategory(search: String) {
        val response = repository.local.getProductsByCategory(search).asLiveData()
        println("VALUE FROM DB ${response}")
        _filteredProducts = response as MutableLiveData<List<ProductEntity>>
    }

    private fun insertProducts(productEntity: List<ProductEntity>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertProducts(productEntity)
        }


    //RETROFIT
    private var _productResponse: MutableLiveData<NetworkResult<List<ProductEntity>>> = MutableLiveData()
    val productResponse: LiveData<NetworkResult<List<ProductEntity>>>
    get() = _productResponse

    fun getProducts() = viewModelScope.launch {
        getProductsSafeCall()
    }

    private suspend fun getProductsSafeCall() {

        _productResponse.value = NetworkResult.Loading()

        if (hasInternetConnection(getApplication())){

            try {
                val response = repository.remote.getProducts()

                for (n in response){
                    println(n.unit)
                }

                _productResponse.value = handleProductResponse(response)


                //Insert to Local DB Here
                val product = productResponse.value!!.data

                if (product != null){
                    offlineCacheProduct(product)
                }

            }catch (e: Exception){
                _productResponse.value = NetworkResult.Error("No Products Found.")
            }

        } else {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            _productResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun offlineCacheProduct(products: List<ProductEntity>) {
        insertProducts(products)
    }

    private fun handleProductResponse(response: List<ProductEntity>): NetworkResult<List<ProductEntity>>? {

        return when {
            response.isNullOrEmpty() -> NetworkResult.Error("No available products")
            else -> {
                NetworkResult.Success(response)
            }
        }

    }

}