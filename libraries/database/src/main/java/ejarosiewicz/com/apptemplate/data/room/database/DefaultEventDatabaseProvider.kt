package ejarosiewicz.com.apptemplate.data.room.database

import android.content.Context
import androidx.room.Room
import ejarosiewicz.com.apptemplate.data.room.dao.TextDataDAO

class DefaultEventDatabaseProvider(val context: Context) : EventsDatabaseProvider {

    val database: TextDataDatabase = Room.databaseBuilder(
        context,
        TextDataDatabase::class.java,
        "text-data-database"
    ).build()

    override fun getDao(): TextDataDAO = database.textDatatDao()

}