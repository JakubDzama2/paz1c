package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Oznam;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.OznamDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VyberOznamFxModel {
    
    private ObservableList<Oznam> oznamy = FXCollections.observableArrayList();
    private ObjectProperty<Oznam> vybratyOznam = new SimpleObjectProperty<>();
    private OznamDao oznamDao = DaoFactory.INSTANCE.getOznamDao();
    private Long ucebnaId;

    VyberOznamFxModel(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        inicializuj();
    }

    public void inicializuj() {
        oznamy.setAll(oznamDao.getByUcebnaId(ucebnaId));
        vybratyOznam.set(null);
    }
    
    public ObservableList<Oznam> getOznamy() {
        return oznamy;
    }

    public void setOznamy(ObservableList<Oznam> oznamy) {
        this.oznamy = oznamy;
    }

    public ObjectProperty<Oznam> getVybratyOznamProperty() {
        return vybratyOznam;
    }
    
    public Oznam getVybratyOznam() {
        return vybratyOznam.get();
    }

    public void setVybratyOznam(Oznam vybratyOznam) {
        this.vybratyOznam.set(vybratyOznam);
    }
    
    
}
