package malvinr.me.tokohijau.data.locale

import android.content.Context
import android.content.SharedPreferences
import malvinr.me.tokohijau.BuildConfig
import malvinr.me.tokohijau.R

class PreferencesManager(private val context: Context) {
    private val sharedPref: SharedPreferences

    companion object {
        val PREF_NAME = BuildConfig.APPLICATION_ID
        val PREF_MIN_PRICE = "min_price"
        val PREF_MAX_PRICE = "max_price"
        val PREF_KEYWORD = "keyword"
        val PREF_WHOLESALE = "wholesale"
        val PREF_OFFICIAL = "official"
    }

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        sharedPref.edit().clear().apply()
    }

    fun getMinPrice(): Int = sharedPref.getInt(PREF_MIN_PRICE, context.resources.getInteger(R.integer.default_min_price))

    fun setMinPrice(minPrice: Int) = sharedPref.edit { putInt(PREF_MIN_PRICE, minPrice) }

    fun getMaxPrice(): Int = sharedPref.getInt(PREF_MAX_PRICE, context.resources.getInteger(R.integer.default_max_price))

    fun setMaxPrice(maxPrice: Int) = sharedPref.edit { putInt(PREF_MAX_PRICE, maxPrice) }

    fun getKeyword(): String = sharedPref.getString(PREF_KEYWORD, null)

    fun setKeyword(keyword: String) = sharedPref.edit { putString(PREF_KEYWORD, keyword) }

    fun getWholesale(): Boolean = sharedPref.getBoolean(PREF_WHOLESALE, false)

    fun setWholesale(isWholesale: Boolean) = sharedPref.edit { putBoolean(PREF_WHOLESALE, isWholesale) }

    fun getOfficialStore(): Boolean = sharedPref.getBoolean(PREF_OFFICIAL, false)

    fun setOfficialStore(isOffical: Boolean) = sharedPref.edit { putBoolean(PREF_OFFICIAL, isOffical) }

    inline fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.block()
        editor.apply()
    }
}