package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberUcebnuFxModel {

    private ObservableList<Ucebna> ucebne = FXCollections.observableArrayList();
    private UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
    private PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
    private ObjectProperty<Ucebna> vybrataUcebna = new SimpleObjectProperty<Ucebna>();
    
    public VyberUcebnuFxModel() {
//        List<String> riadkyString = new ArrayList<>();
//        for (Ucebna ucebna : ucebnaDao.getAll()) {
//            String s = ucebna.getNazov() + "     " + pouzivatelDao.getById(ucebna.getIdPouzivatela()).getMeno();
//            riadkyString.add(s);
//        }
        setVybrataUcebna(null);
        this.ucebne.setAll(ucebnaDao.getAll());
    }

    public ObservableList<Ucebna> getUcebne() {
        return ucebne;
    }

    public void setUcebne(ObservableList<Ucebna> ucebne) {
        this.ucebne = ucebne;
    }

    public Ucebna getVybrataUcebna() {
        return vybrataUcebna.get();
    }

    public ObjectProperty getVybrataUcebnaProperty() {
        return vybrataUcebna;
    }
    
    public void setVybrataUcebna(Ucebna vybrataUcebna) {
        this.vybrataUcebna.set(vybrataUcebna);
    }
    
    
    
    
}
