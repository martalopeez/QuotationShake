package mylophue.quotationshake.data.newquotation

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(private val connectivity: ConnectivityManager) {
    fun isConnectionAvailable(): Boolean {
        val capabilities = connectivity.getNetworkCapabilities(connectivity.activeNetwork)
        return capabilities != null && (capabilities.hasTransport(TRANSPORT_WIFI) || capabilities.hasTransport(TRANSPORT_CELLULAR))
    }
}