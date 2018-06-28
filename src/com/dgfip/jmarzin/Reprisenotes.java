package com.dgfip.jmarzin;

import javax.swing.*;
import java.io.FileOutputStream;

public class Reprisenotes {

    private static String join(String[] eclate) {
        String res = eclate[0];
        for(int i=1; i < eclate.length; i++) {
            res += "|" + eclate[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Areprendre areprendre = new Areprendre();
        Acompleter acompleter = new Acompleter(areprendre.getRepertoire());
        int nbRepris = 0;
        try {
            FileOutputStream fout = new FileOutputStream(acompleter.getPath());
            if(acompleter.getIndexNotesPoste() < 0) {
                fout.write((acompleter.lignesLues.get(0)+"|"+"NOTES POSTE\n").getBytes());
            } else {
                fout.write((acompleter.lignesLues.get(0)+"\n").getBytes());
            }
            for(int i=1; i < acompleter.lignesLues.size(); i++) {
                String ligne = acompleter.getLignesLues().get(i);
                if(ligne.endsWith("|")) ligne += " ";
                String[] eclate = ligne.split("\\|");
                String cle = eclate[acompleter.indexRole].replaceFirst("^0+", "") +
                        eclate[acompleter.indexMer].replaceFirst("^0+", "") +
                        eclate[acompleter.indexCompte].replaceFirst("^0+", "");
                if(areprendre.getDict().containsKey(cle)) {
                    nbRepris++;
                    if(acompleter.getIndexNotesPoste() < 0) {
                        fout.write((acompleter.lignesLues.get(i)+"|"+areprendre.getDict().get(cle)+"\n").getBytes());
                    } else {
                        if(eclate[acompleter.getIndexNotesPoste()].trim().isEmpty()) {
                            eclate[acompleter.getIndexNotesPoste()] = areprendre.getDict().get(cle);
                        } else {
                            eclate[acompleter.getIndexNotesPoste()] += "//" + areprendre.getDict().get(cle);
                        }
                        ligne = join(eclate);
                        fout.write((ligne+"\n").getBytes());
                    }
                } else {
                    if(acompleter.getIndexNotesPoste() < 0) {
                        fout.write((acompleter.lignesLues.get(i)+"|\n").getBytes());
                    } else {
                        fout.write((acompleter.lignesLues.get(i)+"\n").getBytes());
                    }
                }
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Fin du traitement\n" + nbRepris + " commentaires repris.");
    }
}
