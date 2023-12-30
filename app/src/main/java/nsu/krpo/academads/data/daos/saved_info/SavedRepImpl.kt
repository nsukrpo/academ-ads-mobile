package nsu.krpo.academads.data.daos.saved_info

import android.content.Context
import javax.inject.Inject

class SavedRepImpl @Inject constructor(
    private val context: Context
) : SavedRep {
    override fun getSavedUserId(): Long =
        context.getSharedPreferences("LOCAL_USER", Context.MODE_PRIVATE).getLong("USER_ID", 1L)

    override fun setSavedUserId(id: Long) {
        context.getSharedPreferences("LOCAL_USER", Context.MODE_PRIVATE)
            .edit()
            .putLong("USER_ID", id)
            .apply()
    }
}