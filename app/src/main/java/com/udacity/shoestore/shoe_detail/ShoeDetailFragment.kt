package com.udacity.shoestore.shoe_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.btCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_shoeDetailFragment_to_shoeListFragment
            )
        )
        binding.btSave.setOnClickListener {
            saveShoeDetail()
        }

        return binding.root
    }

    private fun saveShoeDetail() {
        val name: String = binding.etName.text.toString()
        val size: Double = binding.etSize.text.toString().toDouble()
        val company: String = binding.etCompany.text.toString()
        val description: String = binding.etDescription.text.toString()

        val newShoe = Shoe(name, size, company, description)
        viewModel.addNewShoe(newShoe)

        findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
    }

}