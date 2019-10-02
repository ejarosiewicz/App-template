package ejarosiewicz.com.apptemplate.data.room.dao

import androidx.room.*
import ejarosiewicz.com.apptemplate.data.room.pojo.TextData

@Dao
interface TextDataDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(textData: TextData)

    @Query("SELECT * FROM text_data WHERE id = :textDataId")
    fun read(textDataId: String): TextData

    @Query("SELECT * FROM text_data")
    fun read(): List<TextData>

    @Update
    fun update(textData: TextData)

    @Query("DELETE FROM text_data WHERE id = :textDataId")
    fun delete(textDataId: Long)
}