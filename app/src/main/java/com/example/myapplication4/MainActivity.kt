package com.example.myapplication4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication4.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calculateTip()}
    }

    private fun calculateTip(){
        val stringIntField=binding.costOfService.text.toString()
        val cost=stringIntField.toDoubleOrNull()
        if(cost==null){
            binding.tipResult.text="";
            return
        }
        val tipPorcentaje=when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent->0.20
            R.id.option_eighteen_percent->0.18
            else->0.15
        }
        var tip=tipPorcentaje*cost
        val roundUp=binding.roundUpSwitch.isChecked
        if(roundUp){
            tip=kotlin.math.ceil(tip)
        }
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount,formattedTip)
    }

}
