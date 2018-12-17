package malvinr.me.tokohijau.feature.filter

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bottom_sheet_filter.*
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.data.locale.PreferencesManager
import malvinr.me.tokohijau.feature.product.ProductParam
import malvinr.me.tokohijau.utils.toast
import org.koin.android.ext.android.inject
import java.text.NumberFormat
import java.util.*

class ProductFilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var minPrice: Int = 0
    private var maxPrice: Int = 0

    private lateinit var listener: ProductFilterListener
    private val preferences: PreferencesManager by inject()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            listener = context as ProductFilterListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ProductFilterListener")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_filter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        switch_filter_wholesale.isChecked = preferences.getWholesale()
        switch_filter_official_store.isChecked = preferences.getOfficialStore()

        minPrice = preferences.getMinPrice()
        maxPrice = preferences.getMaxPrice()

        crsb_filter_range_price
            .setMinStartValue(minPrice.toFloat())
            .setMaxStartValue(maxPrice.toFloat())
            .apply()

        initButtonListener()
    }

    private fun initButtonListener() {
        tv_filter_reset.setOnClickListener {
            activity?.toast("This feature is not available yet")
        }

        btn_filter.setOnClickListener {
            val isWholesale = switch_filter_wholesale.isChecked
            val isOfficialStore = switch_filter_official_store.isChecked

            preferences.setWholesale(isWholesale)
            preferences.setOfficialStore(isOfficialStore)
            preferences.setMinPrice(minPrice)
            preferences.setMaxPrice(maxPrice)

            listener.onFilterSubmit()
            dismiss()
        }

        crsb_filter_range_price.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            val localeID = Locale("in", "ID")
            val rupiahFormat = NumberFormat.getCurrencyInstance(localeID)

            tv_filter_min_price_value.text = rupiahFormat.format(minValue)
            tv_filter_max_price_value.text = rupiahFormat.format(maxValue)
        }

        crsb_filter_range_price.setOnRangeSeekbarFinalValueListener { minValue, maxValue ->
            minPrice = minValue.toInt()
            maxPrice = maxValue.toInt()
        }
    }
}