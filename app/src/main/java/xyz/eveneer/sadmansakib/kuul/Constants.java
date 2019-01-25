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

package xyz.eveneer.sadmansakib.kuul;

import android.Manifest;

public class Constants{
    public static class splash{
        public static final int SPLASH_DELAY = 1500;
    }

    public static class  tutorial{
        public static final String[] PERMISSIONS={
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
    }

    public static class otp{
        public final static int APP_REQUEST_CODE= 99;
    }

    static class oAuth{
        static  final String BASE_URL = "http://kuul.eveneer.xyz/api/";
    }

    public static class sos_id{
        public static final String SOS_ID = "id";
    }
}
