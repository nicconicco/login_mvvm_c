package carlos.nicolau.galves.login_mvvm_c.framework.utils

import carlos.nicolau.galves.login_mvvm_c.framework.MvvmcApplication
import java.io.IOException


object Assets {
    fun readJsonFile(nameFile: String): String {

        var json = "Documento nao encontrado"
        //todo: ver com di
        MvvmcApplication.getAppContext()?.let {
            try {
                val inputStream = it.assets.open(nameFile)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                json = String(buffer, Charsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return json
    }
}