package com.example.passwordmanager

import androidx.lifecycle.*
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.model.PasswordRepository
import kotlinx.coroutines.launch

class PasswordsListViewModel(
    private val passwordRepository: PasswordRepository
) : ViewModel(){

    val allPasswords: LiveData<List<Password>> = passwordRepository.allPasswords.asLiveData()

    fun insert(password: Password) = viewModelScope.launch {
        passwordRepository.insert(password)
    }
    fun delete(password: Password) = viewModelScope.launch {
        passwordRepository.delete(password)
    }
    fun update(password: Password) = viewModelScope.launch {
        passwordRepository.update(password)
    }
}

class PasswordViewModelFactory(private val repository: PasswordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCKECKED_CAST")
        return PasswordsListViewModel(repository) as T
    }
}








