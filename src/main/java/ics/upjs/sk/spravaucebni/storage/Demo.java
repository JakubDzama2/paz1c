package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Ucebna;
import java.util.Collections;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        UcebnaDao dao = DaoFactory.INSTANCE.getUcebnaDao();
        List<Ucebna> ucebne = dao.getAll();
        for (Ucebna u : ucebne) {
            System.out.println(u.getNazov());
        }
    }
}
