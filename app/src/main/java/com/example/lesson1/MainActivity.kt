package com.example.lesson1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerForActivity()
        onClickButton()


    }



    private fun onClickButton(){
        binding.editText.setText(intent.getStringExtra(EXTRA_MESSAGE))
        binding.clickBtn.setOnClickListener() {
            if (binding.editText.text.toString().isEmpty()){
                Toast.makeText(this, getString(R.string.toastEmpty), Toast.LENGTH_SHORT).show()
            }else {
                openActivityForResult()
            }
        }
    }

    private fun registerForActivity() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK){
            binding.editText.setText(result.data?.getStringExtra(TEXT))
        }}
    }

    private fun openActivityForResult(){
        Intent(this, MainActivity2::class.java).apply {
            putExtra(TEXT, binding.editText.text.toString())
            resultLauncher.launch(this)
        }
    }

    companion object{
        const val TEXT = "text"
    }

}