package com.sangyoon.withtrip.ui.signin

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sangyoon.withtrip.R
import com.sangyoon.withtrip.model.User
import kotlinx.android.synthetic.main.extra_info_fragment.*
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import com.google.android.gms.common.util.InputMethodUtils.showSoftInput
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class ExtraInfoFragment : Fragment() {

    companion object {
        const val TAG = "ExtraInfoFragment"

        fun newInstance() = ExtraInfoFragment()
    }

    internal var newUser: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.extra_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        genderMale.isSelected = true

        genderMale.setOnClickListener {
            it.isSelected = true
            genderFemale.isSelected = false
        }
        genderFemale.setOnClickListener {
            genderMale.isSelected = false
            it.isSelected = true
        }

        nameInput.hint = "이름을 입력해주세요"
        nameInput.setText(newUser?.name)
        nameInput.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(nameInput, InputMethodManager.SHOW_IMPLICIT)

        completeButton.setOnClickListener {
            if (!nameInput.text.isNullOrBlank()) {
                newUser?.name = nameInput.text.toString()
                newUser?.gender = if (genderMale.isSelected) "male" else "female"

                (activity as SignInActivity).signInComplete(newUser!!)
            }
        }
    }



}