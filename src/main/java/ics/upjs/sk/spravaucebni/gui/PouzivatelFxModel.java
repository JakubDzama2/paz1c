package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import ics.upjs.sk.spravaucebni.Ucebna;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.PouzivatelDao;
import ics.upjs.sk.spravaucebni.storage.UcebnaDao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PouzivatelFxModel {
    private StringProperty meno = new SimpleStringProperty();
    private StringProperty heslo = new SimpleStringProperty();
    private StringProperty mail = new SimpleStringProperty();
    private UcebnaDao ucebnaDao = DaoFactory.INSTANCE.getUcebnaDao();
    private List<Ucebna> ucebne = ucebnaDao.getAll();
    private List<Ucebna> ucebneBezSpravcu;
    private Map<Long, BooleanProperty> ucebneBezSpravcuMap = new HashMap<>();
    

    public PouzivatelFxModel() {
         
        ucebneBezSpravcu = ucebnaDao.getUcebneBezPouzivatelov();
        for (Ucebna u: ucebneBezSpravcu) {
              ucebneBezSpravcuMap.put(u.getId(), new SimpleBooleanProperty(false));
        }
    }

    public List<Ucebna> getUcebneBezSpravcu() {
        return ucebneBezSpravcu;
    }
    
    public BooleanProperty getUcebnaBezSpravcuBooleanProperty(Long ucebna) {
        return ucebneBezSpravcuMap.get(ucebna);
    }
    
    public StringProperty getMenoProperty() {
        return meno;
    }
    
    public String getMeno() {
        return meno.get();
    }

    public void setMeno(String meno) {
        this.meno.set(meno);
    }
    
    public StringProperty getHesloProperty() {
        return heslo;
    }
    
    public String getHeslo() {
        return heslo.get();
    }

    public void setHeslo(String heslo) {
        this.heslo.set(heslo);
    }
    
    public StringProperty getMailProperty() {
        return mail;
    }
    
    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }
    
    public void ulozPouzivatela(){
//        System.out.println(meno+" "+heslo+" "+mail);
//        for (Map.Entry<Long, BooleanProperty> entry : ucebneBezSpravcuMap.entrySet()) {
//            Long key = entry.getKey();
//            BooleanProperty value = entry.getValue();
//            
//            System.out.println(key + " " + value);
//        }

        Pouzivatel pouzivatel= new Pouzivatel();
        PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
        
        pouzivatel.setMeno(getMeno());
        pouzivatel.setHeslo(getHeslo());
        pouzivatel.setEmail(getMail());
        pouzivatel.setPoslednePrihlasenie(LocalDateTime.now());
        
        pouzivatelDao.save(pouzivatel);
        
        for (Map.Entry<Long, BooleanProperty> entry : ucebneBezSpravcuMap.entrySet()) {
            Long key = entry.getKey();
            BooleanProperty value = entry.getValue();
            
            if(value.getValue()){
                Ucebna ucebna = ucebnaDao.getById(key);
                ucebna.setIdPouzivatela(pouzivatel.getId());
                ucebnaDao.save(ucebna);
            }
        }
        
    
    }
}
