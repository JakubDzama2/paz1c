package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Projektor;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.ProjektorDao;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrehladProjektorovFxModel {
    
    private ObservableList<JedenProjektorFxModel> projektory = FXCollections.observableArrayList();
    private Long pouzivatelId;

    public PrehladProjektorovFxModel(Long pouzivatelId) {
        this.pouzivatelId = pouzivatelId;
        ProjektorDao projektorDao = DaoFactory.INSTANCE.getProjektorDao();
        UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
        List<Projektor> projektors = projektorDao.getByPouzivatelId(pouzivatelId);
        for (Projektor projektor : projektors) {
            JedenProjektorFxModel jpfm = new JedenProjektorFxModel();
            jpfm.setKvalitaObrazu(projektor.getKvalitaObrazu());
            jpfm.setNazovModelu(projektor.getNazovModelu());
            jpfm.setNazovUcebne(ucebnaDao.getById(projektor.getUcebnaId()).getNazov());
            jpfm.setOcakavanaZivotnostLampy(projektor.getOcakavanaZivotnostLampy());
            jpfm.setPocetNasvietenychHodin(projektor.getPocetNasvietenychHodin());
            projektory.add(jpfm);
        }
    }

    public ObservableList<JedenProjektorFxModel> getProjektory() {
        return projektory;
    }

    public void setProjektory(ObservableList<JedenProjektorFxModel> projektory) {
        this.projektory = projektory;
    }
    
    
}
