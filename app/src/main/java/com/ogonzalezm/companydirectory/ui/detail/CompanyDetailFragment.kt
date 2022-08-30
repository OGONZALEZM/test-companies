package com.ogonzalezm.companydirectory.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.databinding.FragmentCompanyDetailBinding
import com.ogonzalezm.companydirectory.ui.form.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyDetailFragment constructor(
  val company: Company?,
  val onEditItemSelected: () -> (Unit),
  val onDeleteItemSelected: () -> (Unit)
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCompanyDetailBinding
    private val viewModel: CompanyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentCompanyDetailBinding.inflate(inflater, container, false)

        company?.let {
            binding.name.text = it.name
            binding.address.text = "Dirección: ${it.address}"
            binding.nit.text = "Nit ${it.nit}"
            binding.phone.text = "Teléfono: ${it.phone}"
        }

        binding.editButton.setOnClickListener {
            this.dismiss()
            onEditItemSelected()
        }

        binding.deleteButton.setOnClickListener {
            this.dismiss()
            onDeleteItemSelected()
        }

        return binding.root
    }

}