package com.github.gwtboot.sample.ui.dncomponents.client.greeting;

import com.dncomponents.client.views.appview.AbstractActivity;
import com.dncomponents.client.views.appview.Place;

public class GreetingPlace extends Place {


    public static final class GreetingPlaceRegister extends PlaceRegister<GreetingPlace> {

        public static GreetingPlaceRegister instance = new GreetingPlaceRegister();

        private GreetingPlaceRegister() {
        }

        private static final String TOKEN = "greeting";

        @Override
        public String getHistoryToken() {
            return TOKEN;
        }

        @Override
        public GreetingPlace getPlaceFromToken(String token) {
            return new GreetingPlace();
        }

        @Override
        public String getTokenFromPlace(GreetingPlace place) {
            return TOKEN;
        }

        @Override
        public AbstractActivity getActivity(GreetingPlace place) {
            return new GreetingActivity(GreetingViewImpl.getInstance(), place);
        }

        @Override
        public Class<? extends Place> forPlace() {
            return GreetingPlace.class;
        }

    }

}