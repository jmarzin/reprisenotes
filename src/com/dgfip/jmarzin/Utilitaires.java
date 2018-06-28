package com.dgfip.jmarzin;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Créé par jmarzin-cp on 29/05/2018.
 */
class Utilitaires {

    private static void erreur(String texte) {
        JOptionPane.showMessageDialog(null,
                texte,
                "Erreur colonne",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
    }

    static int[] indices(String ligne, boolean exclureColonneNotes) {
        int[] result = new int[4];
        String[] entetes = ligne.split("\\|");
        List<String> listeEntetes = Arrays.asList(entetes);
        result[0] = listeEntetes.indexOf("ROLE");
        if (result[0] < 0) {
            erreur("colonne ROLE non trouvée");
        }
        result[1] = listeEntetes.indexOf("MER");
        if (result[1] < 0) {
            erreur("colonne MER non trouvée");
        }
        result[2] = listeEntetes.indexOf("COMPTE");
        if (result[2] < 0) {
            erreur("colonne COMPTE non trouvée");
        }
        result[3] = listeEntetes.indexOf("NOTES POSTE");
        if (result[3] < 0 && !exclureColonneNotes) {
            erreur("colonne NOTES POSTE non trouvée");
        }
        return result;
    }

    static File quelFichier(String repertoire, String libelle1, String libelle2) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(libelle1, "csv");
        final JFileChooser fileChooser = new JFileChooser();
        if(!repertoire.isEmpty()) {
            fileChooser.setCurrentDirectory(new File(repertoire));
        }
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle(libelle2);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            System.exit(0);
        }
        return fileChooser.getSelectedFile();
    }
}
