package malvinr.me.tokohijau

import android.app.Application
import malvinr.me.tokohijau.di.modules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}