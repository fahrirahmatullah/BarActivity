package com.example.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private fun toDouble(str: String): Double? {
    return try {
        str.toDouble()
    } catch (e: NumberFormatException) {
        null
    }
}

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState!= null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View) {
    if (v.id == R.id.btn_calculate){
        val inputLength = edtLength.text.toString().trim { it<= ' '}
        val inputWidth = edtWidth.text.toString().trim{it <= ' '}
        val inputHeight = edtHeight.text.toString().trim(){it <= ' '}

        var isEmptyField = false
        var isInvalidDouble= false

        if (TextUtils.isEmpty(inputLength)) {
            isEmptyField = true
            edtLength.error = "Field ini tidak boleh kosong"

        }

        if (TextUtils.isEmpty(inputWidth)) {
            isEmptyField = true
            edtWidth.error = "field ini tidak boleh kosong"

        }

        if (TextUtils.isEmpty(inputHeight)) {
            isEmptyField = true
            edtHeight.error = "field ini tidak boleh kosong"

        }

        val length = toDouble(inputLength)
        val width = toDouble(inputWidth)
        val height = toDouble(inputHeight)

        if (length == null ) {
            isInvalidDouble = true
            edtLength.error = "Field ini hraus berupa nomer yang valid"

        }

        if (width == null ) {
            isInvalidDouble = true
            edtWidth.error =  "Field ini harus berupa nomer yang valid"

        }
        if (height == null ) {
            isInvalidDouble = true
            edtHeight.error =  "Field ini harus berupa nomer yang valid"

        }

        if (!isEmptyField && !isInvalidDouble) {
            val volume = length as Double * width as Double * height as Double
            tvResult.text = volume.toString()
        }


    }


    }
}
