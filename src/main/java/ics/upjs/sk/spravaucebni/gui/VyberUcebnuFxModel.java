package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberUcebnuFxModel {

    private ObservableList<Pouzivatel> pouzivatelia = FXCollections.observableArrayList();
    private ObjectProperty<Pouzivatel> vybratyPouzivatel = new SimpleObjectProperty<Pouzivatel>();
    private ObservableList<Ucebna> ucebne = FXCollections.observableArrayList();
    private UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
    private PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
    private ObjectProperty<Ucebna> vybrataUcebna = new SimpleObjectProperty<Ucebna>();
    
    public VyberUcebnuFxModel() {
        pouzivatelia.setAll(pouzivatelDao.getAll());
        Pouzivatel p1 = new Pouzivatel();
        p1.setMeno("--bez správcu--");
        pouzivatelia.add(p1);
        Pouzivatel p2 = new Pouzivatel();
        p2.setMeno("--všetci--");
        pouzivatelia.add(p2);
        vybratyPouzivatel.set(p2);
        setVybrataUcebna(null);       
        this.ucebne.setAll(ucebnaDao.getAll());
    }
    
    public void inicializuj() {
        setVybrataUcebna(null);
        if (getVybratyPouzivatel().getMeno().equals("--všetci--")) {
            this.ucebne.setAll(ucebnaDao.getAll());
        } else if (getVybratyPouzivatel().getMeno().equals("--bez správcu--")) {
            this.ucebne.setAll(ucebnaDao.getUcebneBezPouzivatelov());
        } else {
            this.ucebne.setAll(ucebnaDao.getByPouzivatelId(getVybratyPouzivatel().getId()));
        }
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

    public ObservableList<Pouzivatel> getPouzivatelia() {
        return pouzivatelia;
    }

    public void setPouzivatelia(ObservableList<Pouzivatel> pouzivatelia) {
        this.pouzivatelia = pouzivatelia;
    }

    public ObjectProperty<Pouzivatel> getVybratyPouzivatelProperty() {
        return vybratyPouzivatel;
    }
    
    public Pouzivatel getVybratyPouzivatel() {
        return vybratyPouzivatel.get();
    }

    public void setVybratyPouzivatel(Pouzivatel vybratyPouzivatel) {
        this.vybratyPouzivatel.set(vybratyPouzivatel);
        inicializuj();
    }
    
    
    
    
}
