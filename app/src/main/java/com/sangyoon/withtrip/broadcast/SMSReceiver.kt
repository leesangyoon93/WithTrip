package com.sangyoon.withtrip.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage

class SMSReceiver : BroadcastReceiver() {

    @FunctionalInterface
    interface OnSMSReceiveListener {
        fun onReceive(data: String)
    }

    internal var onSMSReceiveListener: OnSMSReceiveListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            val sms = StringBuilder()   // SMS문자를 저장할 곳
            val bundle = intent?.extras        // Bundle객체에 문자를 받아온다

            if (bundle != null) {
                // 번들에 포함된 문자 데이터를 객체 배열로 받아온다
                val pdusObj = bundle.get("pdus") as Array<*>

                // SMS를 받아올 SmsMessage 배열을 만든다
                val messages = mutableListOf<SmsMessage>()
                for (i in 0..pdusObj.size) {
                    messages.add(SmsMessage.createFromPdu(pdusObj[i] as ByteArray))
                }

                for (message in messages) {
                    sms.append(message.messageBody)
                }

                onSMSReceiveListener?.onReceive(sms.toString())
            }
        }
    }

}