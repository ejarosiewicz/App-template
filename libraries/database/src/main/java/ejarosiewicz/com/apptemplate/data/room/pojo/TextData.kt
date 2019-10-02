package ejarosiewicz.com.apptemplate.data.room.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "text_data",
    foreignKeys = [
        ForeignKey(
            entity = AdditionalGibberish::class,
            parentColumns = ["id"],
            childColumns = ["gibberish_id"],
            onDelete = CASCADE
        )]
)
data class TextData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val text: String,
    @ColumnInfo(name = "gibberish_id")
    val gibberishId: Long
)