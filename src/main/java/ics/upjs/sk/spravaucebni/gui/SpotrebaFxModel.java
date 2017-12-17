package ics.upjs.sk.spravaucebni.gui;

import ics.upjs.sk.spravaucebni.Spotreba;
import ics.upjs.sk.spravaucebni.storage.DaoFactory;
import ics.upjs.sk.spravaucebni.storage.SpotrebaDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jfree.data.category.DefaultCategoryDataset;

public class SpotrebaFxModel {

    private IntegerProperty rok = new SimpleIntegerProperty();
    private IntegerProperty mesiac = new SimpleIntegerProperty();
    private ObservableList<JednaSpotrebaFxModel> spotreby = FXCollections.observableArrayList();
    private SpotrebaDao spotrebaDao = DaoFactory.INSTANCE.getSpotrebaDao();
    private Long ucebnaId;
    private int celkovaSpotreba;
    private double priemernaSpotreba;
    private double celkovaCena;
    private static final double PRIEMERNA_CENA_SPOTREBY = 0.15;

    public SpotrebaFxModel(Long ucebnaId) {
        this.ucebnaId = ucebnaId;
        rok.set(LocalDate.now().getYear());
        mesiac.set(LocalDate.now().getMonthValue());
        inicializuj();
    }
    
    private void inicializuj() {
        spotreby.clear();
        List<Spotreba> spotrebyVMesiaci = spotrebaDao.getByDatumAndUcebnaId(getRok(), getMesiac(), ucebnaId);
        celkovaSpotreba = 0;
        celkovaCena = 0;
        for (Spotreba spotreba : spotrebyVMesiaci) {
            JednaSpotrebaFxModel jednaSpotrebaFxModel = new JednaSpotrebaFxModel();
            jednaSpotrebaFxModel.setDatum(spotreba.getDatum());
            jednaSpotrebaFxModel.setHodnota(spotreba.getHodnota());
            jednaSpotrebaFxModel.setCena(spotreba.getHodnota()*PRIEMERNA_CENA_SPOTREBY);
            spotreby.add(jednaSpotrebaFxModel);
            celkovaSpotreba += spotreba.getHodnota();
            celkovaCena += jednaSpotrebaFxModel.getCena();
        }
        
        int pocetSpotrieb = spotrebyVMesiaci.size();
        if (pocetSpotrieb == 0) {
            priemernaSpotreba = 0;
        } else {
            priemernaSpotreba = celkovaSpotreba*1.0/pocetSpotrieb;
        }
        
    }

    public IntegerProperty getRokProperty() {
        return rok;
    }

    public Integer getRok() {
        return rok.get();
    }
    
    public void setRok(Integer rok) {
        this.rok.set(rok);
        inicializuj();
    }

    public IntegerProperty getMesiacProperty() {
        return mesiac;
    }

    public Integer getMesiac() {
        return mesiac.get();
    }
    
    public void setMesiac(Integer mesiac) {
        this.mesiac.set(mesiac);
        inicializuj();
    }

    public ObservableList<JednaSpotrebaFxModel> getSpotreby() {
        return spotreby;
    }

    public void setSpotreby(ObservableList<JednaSpotrebaFxModel> spotreby) {
        this.spotreby = spotreby;
    }

    public int getCelkovaSpotreba() {
        return celkovaSpotreba;
    }

    public void setCelkovaSpotreba(int celkovaSpotreba) {
        this.celkovaSpotreba = celkovaSpotreba;
    }

    public double getPriemernaSpotreba() {
        return priemernaSpotreba;
    }

    public void setPriemernaSpotreba(double priemernaSpotreba) {
        this.priemernaSpotreba = priemernaSpotreba;
    }

    public double getCelkovaCena() {
        return celkovaCena;
    }

    public void setCelkovaCena(double celkovaCena) {
        this.celkovaCena = celkovaCena;
    }
    
    public DefaultCategoryDataset vytvorDataset(boolean spotrebaControl) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      
      String tentoMesiac = "Tento mesiac";
      String minulyMesiac = "Minulý mesiac";
      String predRokom = "Minulý rok v daný mesiac";
      
      int spravnyRok = getRok() - 1;
       List<Spotreba> spotrebyMinulyRok = spotrebaDao.getByDatumAndUcebnaId(spravnyRok, getMesiac(), ucebnaId);
      List<Spotreba> spotrebyMinulyMesiac;
       int spravnyMesiac;
      if(getMesiac() == 1){
          spravnyMesiac = 12;
          spotrebyMinulyMesiac = spotrebaDao.getByDatumAndUcebnaId(spravnyRok, spravnyMesiac, ucebnaId);
      }else{
         spravnyMesiac = getMesiac() - 1;
          spotrebyMinulyMesiac = spotrebaDao.getByDatumAndUcebnaId(getRok(), spravnyMesiac, ucebnaId);
      }
      
      
      
      if(spotrebaControl){
          for(JednaSpotrebaFxModel s :spotreby){
              dataset.addValue(s.getHodnota(),tentoMesiac,Integer.toString(s.getDatum().getDayOfMonth()));
          }
          for(Spotreba s :spotrebyMinulyRok){
              dataset.addValue(s.getHodnota(),predRokom,Integer.toString(s.getDatum().getDayOfMonth()));
          }
          for(Spotreba s :spotrebyMinulyMesiac){
              dataset.addValue(s.getHodnota(),minulyMesiac,Integer.toString(s.getDatum().getDayOfMonth()));
          }
      }else {
          for(JednaSpotrebaFxModel s :spotreby){
              dataset.addValue(s.getCena(),tentoMesiac,Integer.toString(s.getDatum().getDayOfMonth()));
          }
          for(Spotreba s :spotrebyMinulyRok){
              dataset.addValue(s.getHodnota()*PRIEMERNA_CENA_SPOTREBY,predRokom,Integer.toString(s.getDatum().getDayOfMonth()));
          }
          for(Spotreba s :spotrebyMinulyMesiac){
              dataset.addValue(s.getHodnota()*PRIEMERNA_CENA_SPOTREBY,minulyMesiac,Integer.toString(s.getDatum().getDayOfMonth()));
          }
      }
      
      
      return dataset;
   }
    
}
