package com.biprangshu.littlelemonapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biprangshu.littlelemonapp.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val menuRepository: MenuRepository
): ViewModel(){

    var menuItems by mutableStateOf(false)

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set


    init {
        loadMenu()
    }

    fun loadMenu(){
        viewModelScope.launch {
            isLoading=true
            try {
                val response= menuRepository.fetchMenu()
                if(response.isSuccessful){
                    menuItems= (response.body()?.menu?: emptyList()) as Boolean
                }else{
                    errorMessage="Error: ${response.code()}"
                }
            }catch (e: Exception){
                errorMessage=e.message ?: "An Unknown Error Occoured"
            }
            isLoading=false
        }
    }


}