package com.ogonzalezm.companydirectory.ui.form

import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ogonzalezm.companydirectory.R
import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.databinding.FragmentCompanyFormBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyFormFragment : Fragment() {

    private lateinit var binding: FragmentCompanyFormBinding
    private val viewModel: CompanyViewModel by viewModels()

    var companyId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        companyId = requireArguments().getInt("id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyFormBinding.inflate(inflater, container, false)

        binding.saveButton.setOnClickListener {
            saveCompany()
        }
        getCompany()

        return binding.root
    }

    private fun getCompany() {
        if(companyId > 0) {
            viewModel.getCompany(companyId).observe(viewLifecycleOwner) {
                binding.name.text = Editable.Factory.getInstance().newEditable(it.name ?: "")
                binding.address.text = Editable.Factory.getInstance().newEditable(it.address ?: "")
                binding.nit.text = Editable.Factory.getInstance().newEditable(it.nit ?: "")
                binding.phone.text = Editable.Factory.getInstance().newEditable(it.phone ?: "")
            }
        }
    }

    private fun saveCompany() {
        val name = binding.name.text.toString()
        val address = binding.address.text.toString()
        val nit = binding.nit.text.toString()
        val phone = binding.phone.text.toString()

        val newCompany = Company(
            id = if (companyId == 0) null else companyId,
            name = name,
            address = address,
            nit = nit,
            phone = phone
        )

        viewModel.saveCompany(newCompany).observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), if (companyId == 0) R.string.company_created else R.string.company_updated, Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

}