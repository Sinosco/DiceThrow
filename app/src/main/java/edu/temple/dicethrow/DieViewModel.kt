package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModel : ViewModel(){
    private val currentRoll = MutableLiveData<Int>().apply{
        value = 1
    }

    fun getRoll() : LiveData<Int> { return currentRoll } //not MutableLiveData as we do not want it to be changeable

    fun setRoll(roll : Int) { currentRoll.value = roll }


}