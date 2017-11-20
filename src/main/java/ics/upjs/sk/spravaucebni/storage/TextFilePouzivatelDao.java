package ics.upjs.sk.spravaucebni.storage;

import ics.upjs.sk.spravaucebni.Pouzivatel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFilePouzivatelDao implements PouzivatelDao {

    private File subor;
    private final DateTimeFormatter formatter = 
            DateTimeFormatter.ofPattern("d.M.yyyy H:m");

    public TextFilePouzivatelDao(File subor) {
        this.subor = subor;
    }
    
    @Override
    public List<Pouzivatel> getAll() {
        List<Pouzivatel> pouzivatelia = new ArrayList<>();
        try (Scanner scanner = new Scanner(subor)) {
        scanner.nextLine();
        String riadok = scanner.nextLine();
        Scanner scannerRiadku = new Scanner(riadok);
        scannerRiadku.useDelimiter(";");
        Pouzivatel p = new Pouzivatel();
        p.setId(scannerRiadku.nextLong());
        p.setMeno(scannerRiadku.next());
        p.setHeslo(scannerRiadku.next());
        p.setEmail(scannerRiadku.next());
        String poslendePrihlasenie = scannerRiadku.next();
        if (!poslendePrihlasenie.equals("null")) {
            p.setPoslednePrihlasenie(LocalDateTime.parse(poslendePrihlasenie, formatter));
        }
        
        } catch (IOException ex) {
            throw new TextFilePouzivatelDaoException("neviem otvoriť súbor " + subor.getPath()
                    + " na zápis", ex);
        }
        return pouzivatelia;
    }
}
