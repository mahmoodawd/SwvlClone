package com.example.swvlclone.comon.utils

import android.content.Context
import android.telephony.TelephonyManager
import com.canopas.campose.countrypicker.countryList
import com.canopas.campose.countrypicker.model.Country
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import java.util.Locale

fun getCountryCode(countryName: String): String {
    val locales = Locale.getAvailableLocales()
    return locales.first {
        val displayName = it.displayCountry
        displayName.equals(countryName, ignoreCase = true)
    }.country
}

fun getDefaultPhoneCode(context: Context): String {
    val defaultCountry = getDefaultCountryCode(context)
    val defaultCode: Country = countryList(context).first { it.code == defaultCountry }
    return defaultCode.dial_code.ifBlank { "+20" }
}

fun getDefaultCountryCode(context: Context): String {
    val defaultLangCode = Locale.ROOT.country
    val localeCode: TelephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return localeCode.networkCountryIso.ifBlank { defaultLangCode }.uppercase()
}

fun checkPhoneNumber(phone: String, fullPhoneNumber: String, countryCode: String): Boolean {
    val number: Phonenumber.PhoneNumber?
    if (phone.length > 6) {
        return try {
            number = PhoneNumberUtil.getInstance().parse(
                fullPhoneNumber,
                Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name
            )

            PhoneNumberUtil.getInstance().isValidNumberForRegion(number, countryCode.uppercase())
        } catch (ex: Exception) {
            false
        }
    }
    return false
}