package com.ogonzalezm.companydirectory.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ogonzalezm.companydirectory.R
import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.databinding.FragmentCompanyListBinding
import com.ogonzalezm.companydirectory.ui.base.CompanyListAdapter
import com.ogonzalezm.companydirectory.ui.detail.CompanyDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyListFragment : Fragment() {

    private lateinit var binding: FragmentCompanyListBinding
    private val viewModel: CompanyListViewModel by viewModels()

    private lateinit var companyListAdapter: CompanyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyListBinding.inflate(inflater, container, false)

        companyListAdapter = CompanyListAdapter {
            showCompany(it)
        }
        binding.listCompanies.adapter = companyListAdapter

        addObserver()
        loadData()

        binding.createButton.setOnClickListener {
            createCompany()
        }

        return binding.root
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun addObserver() {
        viewModel.companyLiveData.observe(viewLifecycleOwner) {
            companyListAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun createCompany() {
        val action = CompanyListFragmentDirections.actionCompanyListFragmentToCompanyFormFragment(0)
        findNavController().navigate(action)
    }

    private fun showCompany(company: Company) {
        val companyDetailFragment = CompanyDetailFragment(company, {
            editCompany(company)
        }, {
            deleteCompany(company)
        })
        companyDetailFragment.show(parentFragmentManager, "COMPANY_DETAIL")
    }

    private fun editCompany(company: Company?) {
        company?.let {
            val action = CompanyListFragmentDirections.actionCompanyListFragmentToCompanyFormFragment(it.id ?: 0)
            findNavController().navigate(action)
        }
    }

    private fun deleteCompany(company: Company?) {
        company?.let {
            viewModel.deleteCompany(company).observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), resources.getString(R.string.company_deleted), Toast.LENGTH_SHORT).show()
                this.loadData()
            }
        }
    }

}