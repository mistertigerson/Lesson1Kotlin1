package com.example.lesson1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    companion object{
        val EXTRA_MESSAGE = "hello world"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.editText.setText(intent.getStringExtra(MainActivity2.MESSAGE))
        onClickButton()

    }

    fun onClickButton(){
        val intent = Intent(this, MainActivity2::class.java).apply {}
        binding.clickBtn.setOnClickListener() {
            if (binding.editText.text.isEmpty()){
                Toast.makeText(this, "EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            }else {
                intent.putExtra(
                    EXTRA_MESSAGE, binding.editText.text.toString())
                startActivity(intent)
            }
        }
    }
}