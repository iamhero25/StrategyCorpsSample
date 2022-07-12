package com.strategycorps.medlite.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.strategycorps.medlite.data.entities.CategoryEntity
import com.strategycorps.medlite.repository.Repository
import com.strategycorps.medlite.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(application: Application, private val repository: Repository): AndroidViewModel(application) {

    //ROOM DATABASE
    val getLocalCategories: LiveData<List<CategoryEntity>> = repository.local.getLocalCategories().asLiveData()

    private fun insertCategories(categoryEntity: List<CategoryEntity>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertCategories(categoryEntity)
        }


    //RETROFIT
    private val _categoryResponse: MutableLiveData<List<CategoryEntity>> = MutableLiveData()
    val categoryResponse: LiveData<List<CategoryEntity>> get() = _categoryResponse

    fun getCategories() = viewModelScope.launch {
        getCategoriesSafeCall()
    }

    private suspend fun getCategoriesSafeCall() {

        if (NetworkState.hasInternetConnection(getApplication())) {

            try {
                val response = repository.remote.getCategories()

                _categoryResponse.value = response

                //Insert to Local DB Here
                val categories = _categoryResponse.value

                println("CATEGORIES FROM PRODUCTRESPONSE +${categories.toString()} ")

                if (categories != null) {
                    println("CATEGORY NOT NULL")
                    offlineCacheCategories(response)
                }

            } catch (e: Exception) {
                println("ERROR +${e.message} ")                
            }
        }
    }

    private fun offlineCacheCategories(categories: List<CategoryEntity>) {
        println( "CATEGORIES TO BE INSERTED ${categories.toString()}")
        insertCategories(categories)
    }

}