package model;

import java.util.Objects;

public class CategoryModel {

    private int id;
    private String name;
    private int sport_id;
    private int region_id;

    public CategoryModel(int id, String name, int sport_id, int region_id) {
        this.id = id;
        this.name = name;
        this.sport_id = sport_id;
        this.region_id = region_id;
    }

    public CategoryModel(int id, String name, int sport_id) {
        this.id = id;
        this.name = name;
        this.sport_id = sport_id;
    }

    public CategoryModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSport_id() {
        return sport_id;
    }

    public void setSport_id(int sport_id) {
        this.sport_id = sport_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sport_id=" + sport_id +
                ", region_id=" + region_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryModel category = (CategoryModel) o;
        return id == category.id && sport_id == category.sport_id && region_id == category.region_id && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sport_id, region_id);
    }
}
