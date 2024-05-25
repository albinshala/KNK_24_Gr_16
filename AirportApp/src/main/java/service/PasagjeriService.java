package service;

import models.Pasagjeri;
import repository.PasagjeriRepository;

import java.sql.SQLException;

public class PasagjeriService {
    public static Pasagjeri regjistroPasagjerin(Pasagjeri pasagjeri) throws SQLException {

        PasagjeriRepository.insert(pasagjeri);

        return PasagjeriRepository.getByPId(pasagjeri.getPerdoruesi_id());

    }

}