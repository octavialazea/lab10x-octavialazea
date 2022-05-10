package domain.utils;

public class Sort {

    private int ascending;
    private Sort additional;
    private String field;

    private Sort() {
        ascending = 1;
        additional = null;
        field = "";
    }

    public int getAscending() {return ascending;}

    public String getField() {return field;}

    public Sort getAdditional() {return additional;}

    private void setField(String f) {
        field = f;
    }

    public static Sort by(String f) {
        Sort newSort = new Sort();
        newSort.setField(f);
        return newSort;
    }

    public Sort ascending(){
        ascending = 1;
        return this;
    }

    public Sort descending(){
        ascending = -1;
        return this;
    }

    public Sort and(Sort add){
        additional = add;
        return this;
    }

}
