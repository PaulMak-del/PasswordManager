package com.example.passwordmanager.viewModels

import androidx.lifecycle.*
import com.example.passwordmanager.model.Password
import com.example.passwordmanager.model.PasswordRepository
import com.example.passwordmanager.model.User
import kotlinx.coroutines.launch

class PasswordsListViewModel(
    private val passwordRepository: PasswordRepository,
) : ViewModel(){

    fun getPasswords(uid: String, isFavorite: Int): LiveData<List<Password>> {
        return if (isFavorite == 1) {
            passwordRepository.getFavoritePasswords(uid).asLiveData()
        } else {
            passwordRepository.getUsersPasswords(uid).asLiveData()
        }
    }
    fun insertPassword(password: Password) = viewModelScope.launch {
        passwordRepository.insertPassword(password)
    }
    fun insertUser(user: User) = viewModelScope.launch {
        passwordRepository.insertUser(user)
    }
    fun deletePassword(password: Password) = viewModelScope.launch {
        passwordRepository.deletePassword(password)
    }
    fun updatePassword(password: Password) = viewModelScope.launch {
        passwordRepository.updatePassword(password)
    }
    fun updatePasswordById(id: Int, name: String, login: String, password: String) = viewModelScope.launch {
        passwordRepository.updatePasswordById(id, name, login, password)
    }
}

class PasswordViewModelFactory(private val repository: PasswordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return PasswordsListViewModel(repository) as T
    }
}








