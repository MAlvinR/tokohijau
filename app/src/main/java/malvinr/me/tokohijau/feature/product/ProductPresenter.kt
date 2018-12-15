package malvinr.me.tokohijau.feature.product

import io.reactivex.disposables.CompositeDisposable
import malvinr.me.tokohijau.data.DataManager
import malvinr.me.tokohijau.data.ProductParam

class ProductPresenter(private val dataManager: DataManager) {

    private var view: ProductView? = null
    private var disposables: CompositeDisposable? = null

    fun onAttach(view: ProductView) {
        this.view = view
        disposables = CompositeDisposable()
    }

    fun onDetach() {
        view = null
        disposables?.clear()
    }

    fun searchProduct(param: ProductParam) {
        val disposable = dataManager
            .searchProduct(param)
            .subscribe({
                view?.onShowProduct(it)
            }, {
                view?.onShowErrorMessage(it.localizedMessage)
            })

        disposables?.add(disposable)
    }
}