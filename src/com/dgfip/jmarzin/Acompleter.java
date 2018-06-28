package com.dgfip.jmarzin;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Acompleter {

    int getIndexNotesPoste() {
        return indexNotesPoste;
    }

    ArrayList<String> getLignesLues() {
        return lignesLues;
    }

    String getPath() {
        return path;
    }

    int indexMer;
    int indexRole;
    int indexCompte;
    private int indexNotesPoste;
    ArrayList<String> lignesLues;
    private String path;

    Acompleter(String repertoire) {

        path = Utilitaires.quelFichier(repertoire, "Fichier à compléter", "Sélectionner le fichier qui doit être complété").getAbsolutePath();
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            lignesLues = new ArrayList<String>();
            String ligne;
            while ((ligne = br.readLine()) != null) {
                if (!ligne.trim().isEmpty()) {
                    lignesLues.add(ligne);
                }
            }
            br.close();
            if(lignesLues.get(0).endsWith("|")) lignesLues.set(0, lignesLues.get(0) + " ");
            int[] tabIndex = Utilitaires.indices(lignesLues.get(0), true);
            indexRole = tabIndex[0];
            indexMer = tabIndex[1];
            indexCompte = tabIndex[2];
            indexNotesPoste = tabIndex[3];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
