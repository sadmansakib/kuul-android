package xyz.eveneer.sadmansakib.kuul.Data.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.ReportIDDao;
import xyz.eveneer.sadmansakib.kuul.Data.Entity.ReportID;

@Database(entities = ReportID.class, version = 1, exportSchema = false)
public abstract class ReportIDRoomDatabase extends RoomDatabase {
    public abstract ReportIDDao reportIDDao();
    private static volatile ReportIDRoomDatabase reportIDInstance;

    public static ReportIDRoomDatabase getDatabase(final Context context){
        if(reportIDInstance == null){
            synchronized (PhoneNumberRoomDatabase.class){
                if(reportIDInstance == null){
                    reportIDInstance = Room.databaseBuilder(context.getApplicationContext(),
                            ReportIDRoomDatabase.class, "report_id_database")
                            .build();
                }
            }
        }
        return reportIDInstance;
    }
}
