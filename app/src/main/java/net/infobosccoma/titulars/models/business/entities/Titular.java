package net.infobosccoma.titulars.models.business.entities;

import java.util.ArrayList;


public class Titular {
    private int id;
    private String titol, subtitol;


    public Titular(int id, String titol, String subtitol) {
        this.id = id;
        this.titol = titol;
        this.subtitol = subtitol;
    }

    public Titular(String titol, String subtitol) {
        this.titol = titol;
        this.subtitol = subtitol;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getSubtitol() {
        return subtitol;
    }

    public void setSubtitol(String subtitol) {
        this.subtitol = subtitol;
    }

    @SuppressWarnings("serial")
    public static class Llista extends ArrayList<Titular> {

    }

}
