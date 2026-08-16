package Endpoints;

import Utils.FilloUtil;
import Utils.PropertyReaderUtil;


public class APIConstants {


    //Central configuration

        public static final String BASE_URL;
        public static String CREATE_BOOKING;
        public static String UPDATE_BOOKING;

        static {
            try {
                BASE_URL = FilloUtil.fetchDataFromXLSX("Sheet1", "BaseUrl", "Value");

                CREATE_BOOKING = PropertyReaderUtil.readKey("CREATE_BOOKING");
                UPDATE_BOOKING = PropertyReaderUtil.readKey("UPDATE_BOOKING");
            } catch (Exception e) {
                throw new RuntimeException("Failed to load API Configuration",e);
            }
        }


    }

