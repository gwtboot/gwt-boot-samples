package com.github.gwtboot.sample.ui.dncomponents.client.cell;

import com.dncomponents.client.views.appview.AbstractActivity;
import com.dncomponents.client.views.appview.DefaultActivity;
import com.dncomponents.client.views.appview.Place;
import com.dncomponents.client.views.core.EnumLookUp;

import java.util.Objects;

public class CellPlace extends Place {
    {
        this.placeRegister = CellPlaceRegister.instance;
    }

    public enum Type {
        TABLE, TREE, LIST;
        public static EnumLookUp<Type> lookUp = new EnumLookUp<>(Type.values());
    }

    private Type type = Type.TABLE;

    public CellPlace() {
    }

    public CellPlace(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPlace that = (CellPlace) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    public static final class CellPlaceRegister extends PlaceRegister<CellPlace> {

        public static CellPlaceRegister instance = new CellPlaceRegister();

        private CellPlaceRegister() {
        }

        private static final String TOKEN = "cell";

        @Override
        public String getHistoryToken() {
            return TOKEN;
        }

        @Override
        public CellPlace getPlaceFromToken(String token) {
            CellPlace tp = new CellPlace();
            String typeString = token.substring(token.indexOf(DIVIDER) + 1);
            Type type = Type.lookUp.getValue(typeString);
            if (type != null)
                tp.setType(type);
            return tp;
        }

        @Override
        public String getTokenFromPlace(CellPlace place) {
            return TOKEN + DIVIDER + place.type;
        }

        @Override
        public AbstractActivity getActivity(CellPlace place) {
            switch (place.getType()) {
                case TREE:
                    return new DefaultActivity(TreeAppView.getInstance(), place);
                case LIST:
                    return new DefaultActivity(ListAppView.getInstance(), place);
                default:
                    return new DefaultActivity(TableAppView.getInstance(), place);
            }
        }

        @Override
        public Class<? extends Place> forPlace() {
            return CellPlace.class;
        }


    }
}