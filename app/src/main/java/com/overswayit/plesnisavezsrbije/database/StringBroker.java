package com.overswayit.plesnisavezsrbije.database;

import com.overswayit.plesnisavezsrbije.domain.DanceCouple;
import com.overswayit.plesnisavezsrbije.domain.enums.List;

import java.util.ArrayList;

/**
 * Created by Rivendell on 12/12/2016.
 */

public class StringBroker {

    private ArrayList<DanceCouple> pio;
    private ArrayList<DanceCouple> mlo;
    private ArrayList<DanceCouple> oml;
    private ArrayList<DanceCouple> sto;
    private ArrayList<DanceCouple> sen;

    public StringBroker() {
        this.pio = new ArrayList<>();
        this.mlo = new ArrayList<>();
        this.oml = new ArrayList<>();
        this.sto = new ArrayList<>();
        this.sen = new ArrayList<>();
        getSomeDancer();
    }

    public ArrayList<DanceCouple> getAllDanceCouples() {
        ArrayList<DanceCouple> danceCouples = new ArrayList<>();
        danceCouples.addAll(pio);
        danceCouples.addAll(mlo);
        danceCouples.addAll(oml);
        danceCouples.addAll(sto);
        danceCouples.addAll(sen);
        return danceCouples;
    }

    public ArrayList<DanceCouple> getDanceCouples(String category) {
        if (category.equals("PIO")) {
            return pio;
        } else if(category.equals("MLO")) {
            return mlo;
        } else if(category.equals("OML")) {
            return oml;
        } else if(category.equals("STO")) {
            return sto;
        } else if(category.equals("SEN")) {
            return sen;
        } else {
            return null;
        }
    }

    public ArrayList<DanceCouple> getDanceCouples(String category, List list) {
        ArrayList<DanceCouple> danceCouples;
        if (category.equals("PIO")) {
            danceCouples = pio;
        } else if(category.equals("MLO")) {
            danceCouples = mlo;
        } else if(category.equals("OML")) {
            danceCouples = oml;
        } else if(category.equals("STO")) {
            danceCouples = sto;
        } else if(category.equals("SEN")) {
            danceCouples = sen;
        } else {
            danceCouples = new ArrayList<>();
        }

        for (int i = 0; i < danceCouples.size(); i++) {
            DanceCouple danceCouple = danceCouples.get(i);
            if (list == List.PointLatinoList) {
                if (!checkIfItIsOk(danceCouple.getDanceClassLA(), danceCouple.getPointsLA())){
                    danceCouples.remove(i);
                    i--;
                }
            } else if (list == List.PointStandardList) {
                if (!checkIfItIsOk(danceCouple.getDanceClassST(), danceCouple.getPointsST())){
                    danceCouples.remove(i);
                    i--;
                }
            } else if (list == List.RatingLatinoList) {
                if (!checkIfItIsOk(danceCouple.getRejtingLatinoPlace(), danceCouple.getRejtingLatinoPoints())){
                    danceCouples.remove(i);
                    i--;
                }
            } else if (list == List.RatingStandardList) {
                if (!checkIfItIsOk(danceCouple.getRejtingStandardPlace(), danceCouple.getRejtingStandardPoints())){
                    danceCouples.remove(i);
                    i--;
                }
            } else if (list == List.Rating10DanceList) {
                if (!checkIfItIsOk(danceCouple.getRejtingKombinacijaPlace(), danceCouple.getRejtingKombinacijaPoints())){
                    danceCouples.remove(i);
                    i--;
                }
            }
        }
        return danceCouples;
    }

    private boolean checkIfItIsOk(String first, String points) {
        if (first.equals("nema") || points.equals("nema")) {
            return false;
        } else {
            return true;
        }
    }

    private void getSomeDancer() {
        DanceCouple pavlovicMarko = new DanceCouple();
        pavlovicMarko.setId(22692);
        pavlovicMarko.setMale("Pavlović Marko");
        pavlovicMarko.setFemale("Tanasković Natalija");
        pavlovicMarko.setCategory("PIO");
        pavlovicMarko.setClub("BEODANCE - Beograd");
        pavlovicMarko.setPointsLA("94");
        pavlovicMarko.setPointsST("94");
        pavlovicMarko.setDanceClassLA("E");
        pavlovicMarko.setDanceClassST("E");
        pavlovicMarko.setRejtingKombinacijaPlace("nema");
        pavlovicMarko.setRejtingKombinacijaPoints("nema");
        pavlovicMarko.setRejtingLatinoPlace("nema");
        pavlovicMarko.setRejtingLatinoPoints("nema");
        pavlovicMarko.setRejtingStandardPlace("nema");
        pavlovicMarko.setRejtingStandardPoints("nema");
        pio.add(pavlovicMarko);

        DanceCouple panticCanja = new DanceCouple();
        panticCanja.setId(22405);
        panticCanja.setMale("Pantić Vanja");
        panticCanja.setFemale("Pantić Doroteja");
        panticCanja.setCategory("PIO");
        panticCanja.setClub("VRAČAR - Beograd");
        panticCanja.setPointsLA("60");
        panticCanja.setPointsST("60");
        panticCanja.setDanceClassLA("E");
        panticCanja.setDanceClassST("E");
        panticCanja.setRejtingKombinacijaPlace("nema");
        panticCanja.setRejtingKombinacijaPoints("nema");
        panticCanja.setRejtingLatinoPlace("nema");
        panticCanja.setRejtingLatinoPoints("nema");
        panticCanja.setRejtingStandardPlace("nema");
        panticCanja.setRejtingStandardPoints("nema");
        pio.add(panticCanja);

        DanceCouple cajicNovica = new DanceCouple();
        cajicNovica.setId(21530);
        cajicNovica.setMale("Ćajić Novica");
        cajicNovica.setFemale("Ćajić Milica");
        cajicNovica.setCategory("MLO");
        cajicNovica.setClub("NEO-DANCE - Novi Sad");
        cajicNovica.setPointsLA("110");
        cajicNovica.setPointsST("110");
        cajicNovica.setDanceClassLA("D");
        cajicNovica.setDanceClassST("D");
        cajicNovica.setRejtingKombinacijaPlace("nema");
        cajicNovica.setRejtingKombinacijaPoints("nema");
        cajicNovica.setRejtingLatinoPlace("nema");
        cajicNovica.setRejtingLatinoPoints("nema");
        cajicNovica.setRejtingStandardPlace("nema");
        cajicNovica.setRejtingStandardPoints("nema");
        mlo.add(cajicNovica);

        DanceCouple mladenovicAleksa = new DanceCouple();
        mladenovicAleksa.setId(20967);
        mladenovicAleksa.setMale("Mladenović Aleksa");
        mladenovicAleksa.setFemale("Jonjević Sofija");
        mladenovicAleksa.setCategory("MLO");
        mladenovicAleksa.setClub("BOLERO - Požarevac");
        mladenovicAleksa.setPointsLA("50");
        mladenovicAleksa.setPointsST("170");
        mladenovicAleksa.setDanceClassLA("B");
        mladenovicAleksa.setDanceClassST("C");
        mladenovicAleksa.setRejtingKombinacijaPlace("nema");
        mladenovicAleksa.setRejtingKombinacijaPoints("nema");
        mladenovicAleksa.setRejtingLatinoPlace("nema");
        mladenovicAleksa.setRejtingLatinoPoints("nema");
        mladenovicAleksa.setRejtingStandardPlace("nema");
        mladenovicAleksa.setRejtingStandardPoints("nema");
        mlo.add(mladenovicAleksa);

        DanceCouple anticNikola = new DanceCouple();
        anticNikola.setId(20944);
        anticNikola.setMale("Andrić Nikola");
        anticNikola.setFemale("Čavkunović Luna");
        anticNikola.setCategory("OML");
        anticNikola.setClub("DARE TO DANCE - Beograd");
        anticNikola.setPointsLA("937");
        anticNikola.setPointsST("1043");
        anticNikola.setDanceClassLA("B");
        anticNikola.setDanceClassST("B");
        anticNikola.setRejtingLatinoPoints("252");
        anticNikola.setRejtingStandardPoints("293");
        anticNikola.setRejtingKombinacijaPoints("395");
        anticNikola.setRejtingLatinoPlace("1");
        anticNikola.setRejtingStandardPlace("1");
        anticNikola.setRejtingKombinacijaPlace("1");
        oml.add(anticNikola);

        DanceCouple gacicNikola = new DanceCouple();
        gacicNikola.setId(19400);
        gacicNikola.setMale("Gačić Nikola");
        gacicNikola.setFemale("Gačić Natalija");
        gacicNikola.setCategory("OML");
        gacicNikola.setClub("LATINO DANCE - Beograd");
        gacicNikola.setPointsLA("891");
        gacicNikola.setPointsST("680");
        gacicNikola.setDanceClassLA("B");
        gacicNikola.setDanceClassST("B");
        gacicNikola.setRejtingLatinoPoints("20");
        gacicNikola.setRejtingStandardPoints("0");
        gacicNikola.setRejtingKombinacijaPoints("0");
        gacicNikola.setRejtingLatinoPlace("9");
        gacicNikola.setRejtingStandardPlace("10");
        gacicNikola.setRejtingKombinacijaPlace("10");
        oml.add(gacicNikola);

        DanceCouple stojkovicLuka = new DanceCouple();
        stojkovicLuka.setId(19875);
        stojkovicLuka.setMale("Stojković Luka");
        stojkovicLuka.setFemale("Grujić Jovana");
        stojkovicLuka.setCategory("STO");
        stojkovicLuka.setClub("SPIN - Beograd");
        stojkovicLuka.setPointsLA("220");
        stojkovicLuka.setPointsST("161");
        stojkovicLuka.setDanceClassLA("B");
        stojkovicLuka.setDanceClassST("B");
        stojkovicLuka.setRejtingLatinoPoints("72");
        stojkovicLuka.setRejtingStandardPoints("0");
        stojkovicLuka.setRejtingKombinacijaPoints("0");
        stojkovicLuka.setRejtingLatinoPlace("8");
        stojkovicLuka.setRejtingStandardPlace("8");
        stojkovicLuka.setRejtingKombinacijaPlace("8");
        sto.add(stojkovicLuka);

        DanceCouple savinIgot = new DanceCouple();
        savinIgot.setId(18729);
        savinIgot.setMale("Savin Igor");
        savinIgot.setFemale("Todić Sanja");
        savinIgot.setCategory("STO");
        savinIgot.setClub("ALLEGRO - Novi Sad");
        savinIgot.setPointsLA("165");
        savinIgot.setPointsST("147");
        savinIgot.setDanceClassLA("B");
        savinIgot.setDanceClassST("B");
        savinIgot.setRejtingLatinoPoints("59");
        savinIgot.setRejtingStandardPoints("56");
        savinIgot.setRejtingKombinacijaPoints("84");
        savinIgot.setRejtingLatinoPlace("9");
        savinIgot.setRejtingStandardPlace("6");
        savinIgot.setRejtingKombinacijaPlace("5");
        sto.add(savinIgot);

        DanceCouple fabijanDavid = new DanceCouple();
        fabijanDavid.setId(16327);
        fabijanDavid.setMale("Fabijan David");
        fabijanDavid.setFemale("Ukraden Nikolina");
        fabijanDavid.setCategory("SEN");
        fabijanDavid.setClub("ALLEGRO - Novi Sad");
        fabijanDavid.setPointsLA("0");
        fabijanDavid.setPointsST("288");
        fabijanDavid.setDanceClassLA("B");
        fabijanDavid.setDanceClassST("B");
        fabijanDavid.setRejtingLatinoPoints("0");
        fabijanDavid.setRejtingStandardPoints("119");
        fabijanDavid.setRejtingKombinacijaPoints("0");
        fabijanDavid.setRejtingLatinoPlace("23");
        fabijanDavid.setRejtingStandardPlace("7");
        fabijanDavid.setRejtingKombinacijaPlace("10");
        sen.add(fabijanDavid);

        DanceCouple rakicNikola = new DanceCouple();
        rakicNikola.setId(1261);
        rakicNikola.setMale("Rakić Nikola");
        rakicNikola.setFemale("Živković Ljubica");
        rakicNikola.setCategory("SEN");
        rakicNikola.setClub("SPIN - Beograd");
        rakicNikola.setPointsLA("112");
        rakicNikola.setPointsST("568");
        rakicNikola.setDanceClassLA("A");
        rakicNikola.setDanceClassST("B");
        rakicNikola.setRejtingLatinoPoints("112");
        rakicNikola.setRejtingStandardPoints("101");
        rakicNikola.setRejtingKombinacijaPoints("212");
        rakicNikola.setRejtingLatinoPlace("9");
        rakicNikola.setRejtingStandardPlace("8");
        rakicNikola.setRejtingKombinacijaPlace("3");
        sen.add(rakicNikola);
    }

}
