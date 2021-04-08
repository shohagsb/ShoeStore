package com.udacity.shoestore.shoe_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.viewmodel.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

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

        validateInputs()
        binding.viewModel = viewModel

        return binding.root
    }

    private fun validateInputs() {
        viewModel.inputMsg.observe(viewLifecycleOwner, { errorMsg ->
            if (errorMsg.isNullOrEmpty()) {
                saveShoeDetail()
            } else {
                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun saveShoeDetail() {
        viewModel.dataSaved.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                viewModel.navigateComplete()
            }
        })
    }

}