package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    private val shoeMutableList = mutableListOf<Shoe>()
    private var _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    fun addNewShoe(newShoe: Shoe) {
        shoeMutableList.add(newShoe)
        _shoeList.value = shoeMutableList
    }
}