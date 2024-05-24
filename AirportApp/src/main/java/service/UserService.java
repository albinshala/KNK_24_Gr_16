package service;

import models.Perdoruesi;
import repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;


public class UserSevice {

    public static Perdoruesi login(String username, String password) throws SQLException {
        Perdoruesi loginUser = UserRepository.getByUsername(username);

        if (loginUser == null) {
            // throw new SQLException("Username not found!");
            return null;
        }

        boolean isPasswordCorrect = PasswordHasher.compareSaltedHash(password, loginUser.getSalt(),
                loginUser.getFjalekalimi_salted());

        if (isPasswordCorrect) {
            return loginUser;
        }

        return null;
    }

    public static Perdoruesi signUp(String emri, String mbiemri, String username, String fjalekalimi,
                                    char gjinia, boolean admin, Date ditelindja) throws SQLException {
        String salted = PasswordHasher.generateSalt();
        String fjalekalimi_salted = PasswordHasher.generateSaltedHash(fjalekalimi, salted);
        Perdoruesi perdoruesi = new Perdoruesi(0, emri, mbiemri, username, fjalekalimi_salted,
                salted, gjinia, admin,ditelindja);
        UserRepository.insert(perdoruesi);

        return UserRepository.getByUsername(username);

    }

    public static Perdoruesi editPassword(Perdoruesi perdoruesi, String oldPassword, String newPassword) throws SQLException {
        boolean isOldPasswordCorrect = PasswordHasher.compareSaltedHash(oldPassword, perdoruesi.getSalt(),
                perdoruesi.getFjalekalimi_salted());
        if (isOldPasswordCorrect){
            perdoruesi.setFjalekalimi_salted(PasswordHasher.generateSaltedHash(newPassword, perdoruesi.getSalt()));
            UserRepository.updatePassword(perdoruesi);
            return perdoruesi;

        }
        return null;
    }

    public static Perdoruesi editProfile(Perdoruesi perdoruesi, String username, String emri, String mbiemri, Date ditelindja) throws SQLException {
        perdoruesi.setUsername(username);
        perdoruesi.setEmri(emri);
        perdoruesi.setMbiemri(mbiemri);
        perdoruesi.setDitelindja(ditelindja);
        UserRepository.update(perdoruesi);
        return perdoruesi;
    }

    public static boolean validUsername(String username, int id) throws SQLException {
        Perdoruesi perdoruesi = UserRepository.getByUsername(username);
        if (perdoruesi == null || perdoruesi.getId() == id){
            return true;
        }
        return false;
    }



}