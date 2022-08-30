package com.ogonzalezm.companydirectory.repository

import com.ogonzalezm.companydirectory.data.Company
import com.ogonzalezm.companydirectory.database.dao.CompanyDAO
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val companyDAO: CompanyDAO
) {

    fun getCompanies() : List<Company> {
        return companyDAO.getAll()
    }

    fun getCompany(id: Int) = companyDAO.getCompany(id)

    fun saveCompany(company: Company) = companyDAO.create(company)

    fun deleteCompany(id: Int) = companyDAO.deleteCompany(id)

}