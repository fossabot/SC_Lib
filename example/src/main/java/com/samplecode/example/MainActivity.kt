package com.samplecode.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samplecode.example.databinding.ActivityMainBinding
import com.samplecode.lib.styles.styleableBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textBold.text = binding.textBold.styleableBuilder().bold().build()
        binding.textBoldLine.text = binding.textBoldLine.styleableBuilder().bold().lineThrough().build()
        binding.textLine.text = binding.textLine.styleableBuilder().lineThrough().build()
        binding.textItalic.text = binding.textItalic.styleableBuilder().italic().build()
    }
}