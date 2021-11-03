package com.example.lesson1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lesson1.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding
    companion object{
        val MESSAGE = "hello world"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        binding.editText.setText(message)
        onClick()
    }

    fun onClick(){
        val intent = Intent(this, MainActivity::class.java).apply {}
        binding.clickBtn.setOnClickListener(){
            if (binding.editText.text.isEmpty()){
                Toast.makeText(this, "EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            }else{
                intent.putExtra(MESSAGE, binding.editText.text.toString())
                startActivity(intent)
            }
        }
    }
}