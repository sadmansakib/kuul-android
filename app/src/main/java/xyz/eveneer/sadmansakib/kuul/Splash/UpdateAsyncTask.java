/*
 *
 *    Copyright 2019  Sadman Sakib
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

package xyz.eveneer.sadmansakib.kuul.Splash;

import android.os.AsyncTask;

import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.Entity.PhoneNumber;

class UpdateAsyncTask extends AsyncTask<PhoneNumber,Void,Void> {
    private PhoneNumberDao mPhoneDao;

    UpdateAsyncTask(PhoneNumberDao phoneDao) {
        this.mPhoneDao=phoneDao;
    }

    @Override
    protected Void doInBackground(PhoneNumber... phoneNumbers) {
        mPhoneDao.update(phoneNumbers[0]);
        return null;
    }
}
