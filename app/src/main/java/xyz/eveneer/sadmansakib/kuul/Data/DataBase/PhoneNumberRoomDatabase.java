/*
 *
 *    Copyright 2018  Sadman Sakib
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package xyz.eveneer.sadmansakib.kuul.Data.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.Entity.PhoneNumber;

@Database(entities = PhoneNumber.class, version = 1)
public abstract class PhoneNumberRoomDatabase extends RoomDatabase {

    public abstract PhoneNumberDao phoneNumberDao();
    private static volatile PhoneNumberRoomDatabase phonenumberInstance;

    public static PhoneNumberRoomDatabase getDatabase(final Context context){
        if(phonenumberInstance == null){
            synchronized (PhoneNumberRoomDatabase.class){
                if(phonenumberInstance == null){
                    phonenumberInstance = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneNumberRoomDatabase.class, "phone_number_database")
                            .build();
                }
            }
        }
        return phonenumberInstance;
    }
}
