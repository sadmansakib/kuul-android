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
}
