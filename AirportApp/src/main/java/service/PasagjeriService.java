package service;

import models.Pasagjeri;
import models.Perdoruesi;
import repository.PasagjeriRepository;
import repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;

public class PasagjeriService {
    public static Pasagjeri regjistroPasagjerin(Pasagjeri pasagjeri) throws SQLException {

        PasagjeriRepository.insert(pasagjeri);

        return PasagjeriRepository.getByPId(pasagjeri.getPerdoruesi_id());

    }

}