package com.mad.StayCoza;

import android.provider.BaseColumns;

public final class FlightContract {
    private FlightContract() {}

    public static class FlightEntry implements BaseColumns {
        public static final String TABLE_NAME = "flights";
        public static final String COLUMN_DEPARTURE = "departure";
        public static final String COLUMN_ARRIVAL = "arrival";
        public static final String COLUMN_DEPARTURE_TIME = "departure_time";
        public static final String COLUMN_ARRIVAL_TIME = "arrival_time";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_AIRLINE = "airline";
    }
}
