package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Chyba;
import ics.upjs.sk.spravaucebni.Pocitac;
import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.Spotreba;
import ics.upjs.sk.spravaucebni.Tabula;
import ics.upjs.sk.spravaucebni.Ucebna;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        UcebnaDao dao = DaoFactory.INSTANCE.getUcebnaDao();
        List<Ucebna> ucebne = dao.getAll();
        
        
        ChybaDao chybaDao = DaoFactory.INSTANCE.getChybaDao();
        List<Chyba> chyby = chybaDao.getAll();
        
        PocitacDao pocitacDao = DaoFactory.INSTANCE.getPocitacDao();
        List<Pocitac> pocitace = pocitacDao.getByUcebnaId(1L);
        
        PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
        List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
        
        ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
        List<Projektor> projektory = projektorDao.getAll();
        
        /*
        Spotreba spotreba = new Spotreba();
        spotreba.setId(2L);
        spotreba.setCas(LocalDateTime.now());
        spotreba.setUcebnaId(1L);
        spotreba.setHodnota(230);
        spotrebaDao.save(spotreba);
*/
        SpotrebaDao spotrebaDao = DaoFactory.INSTANCE.getSpotrebaDao();

        List<Spotreba> spotreby = spotrebaDao.getAll();
        
        TabulaDao tabulaDao = DaoFactory.INSTANCE.getTabulaDao();
        List<Tabula> tabule = tabulaDao.getAll();
        
        for (Ucebna u : ucebne) {
            System.out.println(u.getNazov());
        }
        for (Chyba chyba : chyby) {
            System.out.println(chyba.getCas() + " " + chyba.getHlasatelChyby());
        }
        for (Pocitac pocitac : pocitace) {
            System.out.println(pocitac.getSerioveCislo());
        }
        for (Pouzivatel pouzivatel : pouzivatelia) {
            System.out.println(pouzivatel.getMeno());
        }
        for (Projektor projektor : projektory) {
            System.out.println(projektor.getNazovModelu());
        }

        for (Spotreba s : spotreby) {
            System.out.println(s.getHodnota());
        }
        for (Tabula tabula : tabule) {
            System.out.println(tabula.getTyp());
        }
    }
}
