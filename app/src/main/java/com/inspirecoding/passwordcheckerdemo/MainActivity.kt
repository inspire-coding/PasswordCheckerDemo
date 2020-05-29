package com.inspirecoding.passwordcheckerdemo

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity()
{
    private var email_input_field: EditText? = null
    private var password_input_field: EditText? = null
    private var email_error_text: TextView? = null
    private var password_error_text: TextView? = null
    private var isAtLeast8Parent: CardView? = null
    private var hasUppercaseParent: CardView? = null
    private var hasNumberParent: CardView? = null
    private var hasSymbolParent: CardView? = null
    private var registration_button: RelativeLayout? = null
    private var registration_button_parent: CardView? = null

    private var isAtLeast8 = false
    private var hasUppercase = false
    private var hasNumber = false
    private var hasSymbol = false
    private var isRegistrationClickable = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setType()
        inputChange()
        setOnClickRegistration()
    }

    private fun setType()
    {
        email_input_field = findViewById(R.id.email_input_field)
        email_error_text = findViewById(R.id.email_error_text)
        password_error_text = findViewById(R.id.password_error_text)
        password_input_field = findViewById(R.id.password_input_field)
        isAtLeast8Parent = findViewById(R.id.p_item_1_icon_parent)
        hasUppercaseParent = findViewById(R.id.p_item_2_icon_parent)
        hasNumberParent = findViewById(R.id.p_item_3_icon_parent)
        hasSymbolParent = findViewById(R.id.p_item_4_icon_parent)
        registration_button = findViewById(R.id.registration_button)
        registration_button_parent = findViewById(R.id.registration_button_parent)
    }

    private fun inputChange()
    {
        email_input_field!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) { }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int)
            {
                registrationDataCheck()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        password_input_field!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int)
            {
                registrationDataCheck()
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
    @SuppressLint("ResourceType")
    private fun registrationDataCheck()
    {
        val password = password_input_field!!.text.toString()
        val email = email_input_field!!.text.toString()
        checkEmpty(email, password)
        if (password.length >= 8)
        {
            isAtLeast8 = true
            isAtLeast8Parent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }
        else
        {
            isAtLeast8 = false
            isAtLeast8Parent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }

        if (password.matches(Regex("(.*[A-Z].*)")))
        {
            hasUppercase = true
            hasUppercaseParent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }
        else
        {
            hasUppercase = false
            hasUppercaseParent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }

        if (password.matches(Regex("(.*[0-9].*)")))
        {
            hasNumber = true
            hasNumberParent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }
        else
        {
            hasNumber = false
            hasNumberParent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }

        if (password.matches(Regex("^(?=.*[_.()]).*$")))
        {
            hasSymbol = true
            hasSymbolParent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        }
        else
        {
            hasSymbol = false
            hasSymbolParent!!.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }

        checkAllData(email)
    }

    private fun checkEmpty(email: String, password: String)
    {
        if (password.length > 0 && password_error_text!!.visibility == View.VISIBLE)
        {
            password_error_text!!.visibility = View.GONE
        }
        if (email.length > 0 && email_error_text!!.visibility == View.VISIBLE)
        {
            email_error_text!!.visibility = View.GONE
        }
    }

    @SuppressLint("ResourceType")
    private fun checkAllData(email: String)
    {
        if (isAtLeast8 && hasUppercase && hasNumber && hasSymbol && email.length > 0)
        {
            isRegistrationClickable = true
            registration_button_parent!!.setCardBackgroundColor(
                Color.parseColor(
                    getString(R.color.colorCheckOk)
                )
            )
        }
        else
        {
            isRegistrationClickable = false
            registration_button_parent!!.setCardBackgroundColor(
                Color.parseColor(
                    getString(R.color.colorCheckNo)
                )
            )
        }
    }

    private fun setOnClickRegistration()
    {
        registration_button!!.setOnClickListener {
            val email = email_input_field!!.text.toString()
            val password = password_input_field!!.text.toString()
            if (email.length > 0 && password.length > 0)
            {
                if (isRegistrationClickable)
                {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.registration_title),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else
            {
                if (email.length == 0)
                {
                    email_error_text!!.visibility = View.VISIBLE
                }
                if (password.length == 0)
                {
                    password_error_text!!.visibility = View.VISIBLE
                }
            }
        }
    }
}
