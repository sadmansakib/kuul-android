package xyz.eveneer.sadmansakib.kuul.Data.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "report_ID")
public class ReportID {
    @PrimaryKey
    @NonNull
    private String ID;

    public ReportID(@NonNull String ID) {
        this.ID = ID;
    }
}
