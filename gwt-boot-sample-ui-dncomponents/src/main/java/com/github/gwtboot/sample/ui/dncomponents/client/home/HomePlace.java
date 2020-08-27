package com.github.gwtboot.sample.ui.dncomponents.client.home;

import com.dncomponents.client.views.appview.AbstractActivity;
import com.dncomponents.client.views.appview.DefaultActivity;
import com.dncomponents.client.views.appview.Place;

public class HomePlace extends Place {


    public static final class HomePlaceRegister extends PlaceRegister<HomePlace> {

        public static HomePlaceRegister instance = new HomePlaceRegister();

        private HomePlaceRegister() {
        }

        private static final String TOKEN = "home";

        @Override
        public String getHistoryToken() {
            return TOKEN;
        }

        @Override
        public HomePlace getPlaceFromToken(String token) {
            return new HomePlace();
        }

        @Override
        public String getTokenFromPlace(HomePlace place) {
            return TOKEN;
        }

        @Override
        public AbstractActivity getActivity(HomePlace place) {
            return new DefaultActivity(HomeViewImpl.getInstance(), place);
        }

        @Override
        public Class<? extends Place> forPlace() {
            return HomePlace.class;
        }
    }
}