package com.ogonzalezm.companydirectory.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject
constructor(
    private val companyRepository: CompanyRepository
) : ViewModel() {

    private var _companyLiveData: MutableLiveData<List<Company>> = MutableLiveData()
    val companyLiveData: LiveData<List<Company>>
        get() = _companyLiveData

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val companies = companyRepository.getCompanies()
            _companyLiveData.postValue(companies)
        }
    }

    fun deleteCompany(company: Company) =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(companyRepository.deleteCompany(company.id ?: 0))
        }

}