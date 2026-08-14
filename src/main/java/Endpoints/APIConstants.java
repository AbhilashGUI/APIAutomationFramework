package Endpoints;

import Utils.FilloUtil;
import Utils.PropertyReaderUtil;
import com.codoid.products.exception.FilloException;

public class APIConstants {


    //Central configuration

        public static String BASE_URL;

        static {
            try {
                BASE_URL = FilloUtil.fetchDataFromXLSX("Sheet1","baseurl","Value");
            } catch (FilloException e) {
                e.printStackTrace();
            }
        }

          //public static String BASE_URL;

        public static String CREATE_BOOKING;
        public static String UPDATE_BOOKING;

        static {
            try {
          //BASE_URL = PropertyReaderUtil.readyKey("url");

                CREATE_BOOKING = PropertyReaderUtil.readKey("CREATE_BOOKING");
                UPDATE_BOOKING = PropertyReaderUtil.readKey("UPDATE_BOOKING");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

