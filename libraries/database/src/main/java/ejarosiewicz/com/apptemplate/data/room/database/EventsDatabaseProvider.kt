package ejarosiewicz.com.apptemplate.data.room.database

import ejarosiewicz.com.apptemplate.data.room.dao.TextDataDAO

interface EventsDatabaseProvider {

    fun getDao(): TextDataDAO
}