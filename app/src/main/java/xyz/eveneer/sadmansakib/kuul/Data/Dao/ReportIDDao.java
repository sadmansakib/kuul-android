package xyz.eveneer.sadmansakib.kuul.Data.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import xyz.eveneer.sadmansakib.kuul.Data.Entity.ReportID;

@Dao
public interface ReportIDDao {

    @Insert
    void insertID(ReportID reportID);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateID(ReportID reportID);

    @Query("SELECT ID FROM report_ID")
    String getID();
}
