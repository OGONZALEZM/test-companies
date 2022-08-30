package com.ogonzalezm.companydirectory.ui.form

import androidx.lifecycle.*
import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val companyRepository: CompanyRepository
) : ViewModel() {

    private var _companyLiveData: MutableLiveData<Company> = MutableLiveData()
    val companyLiveData: LiveData<Company>
        get() = _companyLiveData

    fun saveCompany(company: Company) =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(companyRepository.saveCompany(company))
        }

    fun getCompany(id: Int) =
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(companyRepository.getCompany(id))
        }
}