package com.example.lesson1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lesson1.MainActivity.Companion.TEXT
import com.example.lesson1.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var viewBinding : ActivityMain2Binding
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        registerForActivity()
        onClick()
    }

    fun onClick(){
        viewBinding.editText.setText(intent.getStringExtra(TEXT))
        viewBinding.clickBtn.setOnClickListener(){
            if (viewBinding.editText.text.isEmpty()){
                Toast.makeText(this, "EditText не может быть пустым", Toast.LENGTH_SHORT).show()
            }else openActivityForResult()
        }
    }

    private fun registerForActivity() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK){
                    viewBinding.editText.setText(result.data?.getStringExtra(TEXT))
                }}
    }
    fun openActivityForResult(){
        Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, viewBinding.editText.text.toString())
            setResult(Activity.RESULT_OK, this)
            finish()
        }

    }
}