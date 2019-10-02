package ejarosiewicz.com.apptemplate.data.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "additional_gibberish")
data class AdditionalGibberish(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val gibberish: String
)