package com.udacity.shoestore.shoe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        getShoeList()
        binding.fabShoeDetail.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_shoeListFragment_to_shoeDetailFragment
            )
        )
        return binding.root
    }


    private fun getShoeList() {
        viewModel.shoeList.observe(viewLifecycleOwner, { shoeList ->
            updateUI(shoeList)
        })
    }

    private fun updateUI(shoeList: List<Shoe>) {
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        param.setMargins(0, 4, 0, 0)
        for (i in shoeList) {
            val nameTv = TextView(context)
            nameTv.layoutParams = param
            nameTv.text = String.format("Shoe Name: ${i.name}")
            val sizeTv = TextView(context)
            sizeTv.layoutParams = param
            sizeTv.text = String.format("Size: ${i.size}")
            val companyTv = TextView(context)
            param.setMargins(0, 4, 0, 12)
            companyTv.layoutParams = param
            companyTv.text = String.format("Company: ${i.company}")
            binding.shoeListLayout.addView(nameTv)
            binding.shoeListLayout.addView(sizeTv)
            binding.shoeListLayout.addView(companyTv)
        }
    }


}