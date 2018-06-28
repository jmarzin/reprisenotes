package com.dgfip.jmarzin;

/**
 * Created by jmarzin-cp on 28/06/2018.
 */
public class Result {
    public int getPos_role() {
        return pos_role;
    }

    public void setPos_role(int pos_role) {
        this.pos_role = pos_role;
    }

    public int getPos_mer() {
        return pos_mer;
    }

    public void setPos_mer(int pos_mer) {
        this.pos_mer = pos_mer;
    }

    public int getPos_compte() {
        return pos_compte;
    }

    public void setPos_compte(int pos_compte) {
        this.pos_compte = pos_compte;
    }

    public int getPos_notes() {
        return pos_notes;
    }

    public void setPos_notes(int pos_notes) {
        this.pos_notes = pos_notes;
    }

    public int getNb_col_notes() {
        return nb_col_notes;
    }

    public void setNb_col_notes(int nb_col_notes) {
        this.nb_col_notes = nb_col_notes;
    }

    private int pos_role;
    private int pos_mer;
    private int pos_compte;
    private int pos_notes;
    private int nb_col_notes;

    public String getNoms_col() {
        return noms_col;
    }

    public void setNoms_col(String noms_col) {
        this.noms_col = noms_col;
    }

    private String noms_col;
    public Result() {
        pos_role = -1;
        pos_mer = -1;
        pos_notes = -1;
        nb_col_notes = 0;
        noms_col = "";
    }
}
