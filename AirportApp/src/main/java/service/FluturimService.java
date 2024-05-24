package service;

import models.Aeroporti;
import models.Airoplani;
import models.Bileta;
import models.Fluturimet;
import repository.AeroportiRepository;
import repository.AiroplaniRepository;
import repository.BiletaRepository;
import repository.FluturimetRepository;

import java.sql.SQLException;

public class FluturimService {

    public static int aeroplaniId;
    public static int aeroportiNisjesiId;
    public static int aeroportiArritjesiId;

    public static void shtoFluturim(Fluturimet fluturim) throws SQLException {

        FluturimetRepository.insert(fluturim);

    }

    public static void shtoObjektetTjera(Airoplani airoplani, Aeroporti aeroporti_nisjes, Aeroporti aeroporti_arritjes) throws Exception{
        aeroplaniId = AiroplaniRepository.insert(airoplani);
        aeroportiNisjesiId = AeroportiRepository.insert(aeroporti_nisjes);
        aeroportiArritjesiId = AeroportiRepository.insert(aeroporti_arritjes);
    }
}
