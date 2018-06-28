package com.dgfip.jmarzin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Areprendre {

    String getRepertoire() {
        return repertoire;
    }
    Map<String, String> getDict() {
        return dict;
    }
    private String repertoire;
    private Map<String, String> dict = new HashMap<String, String>();

    Areprendre() {

        File fichier = Utilitaires.quelFichier("", "Fichier à reprendre", "Sélectionner le fichier dont les commentaires sont à reprendre");
        this.repertoire = fichier.getParent();
        String path = fichier.getAbsolutePath();
        try{
            InputStream ips=new FileInputStream(path);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            ArrayList<String> lignesLues = new ArrayList<String>();
            String ligne;
            while ((ligne=br.readLine())!=null){
                if(!ligne.trim().isEmpty()) {
                    lignesLues.add(ligne);
                }
            }
            br.close();
            if(lignesLues.get(0).endsWith("|")) lignesLues.set(0, lignesLues.get(0) + " ");
            int[] tabIndex = Utilitaires.indices(lignesLues.get(0), false);
            int indexRole = tabIndex[0];
            int indexMer = tabIndex[1];
            int indexCompte = tabIndex[2];
            int indexNotesPoste = tabIndex[3];
            for(int i =1; i < lignesLues.size(); i++) {
                if(lignesLues.get(i).endsWith("|")) lignesLues.set(i, lignesLues.get(i) + " ");
                String[] ligneEclatee = lignesLues.get(i).split("\\|");
                System.out.println(lignesLues.get(i));
                String note = ligneEclatee[indexNotesPoste];
                for (int j = indexNotesPoste + 1 ; j < ligneEclatee.length ; j++) {
                    note += "|" + ligneEclatee[j];
                }
                if(!note.isEmpty()) {
                    String cle = ligneEclatee[indexRole].replaceFirst("^0+", "") +
                            ligneEclatee[indexMer].replaceFirst("^0+", "") +
                            ligneEclatee[indexCompte].replaceFirst("^0+", "");
                    if (dict.containsKey(cle)) {
                        dict.put(cle, dict.get(cle) + "//" + note);
                    } else {
                        dict.put(cle, note);
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}