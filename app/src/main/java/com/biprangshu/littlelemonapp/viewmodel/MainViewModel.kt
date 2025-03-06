package com.biprangshu.littlelemonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biprangshu.littlelemonapp.data.remote.Menu
import com.biprangshu.littlelemonapp.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _menuItems = MutableStateFlow<List<Menu>>(emptyList()) // ✅ Corrected type
    val menuItems: StateFlow<List<Menu>> = _menuItems.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadMenu()
    }

    fun loadMenu() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null // Clear any previous error
            try {
                val response = menuRepository.fetchMenu()
                if (response.isSuccessful) {
                    _menuItems.value = response.body()?.menu ?: emptyList()
                } else {
                    _errorMessage.value = "Server error: ${response.code()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Network error. Please check your internet connection."
            } catch (e: HttpException) {
                _errorMessage.value = "Server error: ${e.code()}"
            } catch (e: Exception) {
                _errorMessage.value = "An unexpected error occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
