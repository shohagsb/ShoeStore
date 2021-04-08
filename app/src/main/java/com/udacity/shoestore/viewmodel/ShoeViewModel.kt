package com.udacity.shoestore.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.BR
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel(), Observable {
    private val propertyChangeRegistry = PropertyChangeRegistry()
    private var _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var _dataSaved = MutableLiveData<Boolean>()
    val dataSaved: LiveData<Boolean>
        get() = _dataSaved

    private var _inputMsg = MutableLiveData<String?>()
    val inputMsg: LiveData<String?>
        get() = _inputMsg

    // Dummy Shoe Info
    private val shoeListDummy = mutableListOf(
        Shoe(
            "STAN SMITH SHOES",
            5.5,
            "Adidas",
            "This product is made with Primegreen, a series of high-performance recycled materials. 50% of upper is recycled content. No virgin polyester."
        ),
        Shoe(
            "CLIMACOOL VENTO SHOES",
            5.5,
            "Adidas",
            "Don't shrink away from the sun. Harness its energy as you hit the pavement in these adidas running shoes. The super breathable mesh upper with HEAT.RDY maximizes airflow so you stay cool and ventilated even when the temperature rises and the heat builds. Segmented Boost cushioning returns energy with every take-off and landing, keeping your legs fresh and your mind free to focus on the clear blue skies."
        )
    )

    init {
        _dataSaved.value = false
        _shoeList.value = shoeListDummy
        _inputMsg.value = null
    }

    // Shoe detail two-way binding
    @Bindable
    var newShoe = Shoe(
        "",
        0.0,
        "",
        ""
    )
        set(value) {
            if (value != field) {
                field = value
                propertyChangeRegistry.notifyChange(this, BR.newShoe)
            }
        }

    // Add new shoe item
    fun addNewShoe() {
        newShoe.let {
            if (isValidateInputs()) {
                _shoeList.value?.add(newShoe)
                _dataSaved.value = true
            }
        }
    }

    fun navigateComplete() {
        _dataSaved.value = false
        newShoe = Shoe(
            "",
            0.0,
            "",
            ""
        )
    }

    // Shoe Detail Inputs validation
    private fun isValidateInputs(): Boolean {
        when {
            newShoe.name.isEmpty() -> {
                _inputMsg.value = "Enter Shoe Name"
                return false
            }
            newShoe.company.isEmpty() -> {
                _inputMsg.value = "Enter Company"
                return false
            }
            newShoe.size <= 0.0 -> {
                _inputMsg.value = "Enter Size"
                return false
            }
            newShoe.description.isEmpty() -> {
                _inputMsg.value = "Enter Description"
                return false
            }
            else -> return true
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }


}