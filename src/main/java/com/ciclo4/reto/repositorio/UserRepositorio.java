package com.ciclo4.reto.repositorio;


import com.ciclo4.reto.interfaces.InterfaceUser;
import com.ciclo4.reto.modelo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
/**
 *
 */
public class UserRepositorio {

    @Autowired
    /**
     *
     */
    private InterfaceUser interfaceUser;

    /**
     *
     * @return
     */
    public List<User> getAll() {
        return (List<User>) interfaceUser.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<User> getUser(int id) {
        return interfaceUser.findById(id);
    }

    /**
     *
     * @param user
     * @return
     */
    public User create(User user) {
        return interfaceUser.save(user);
    }

    /**
     *
     * @param user
     */
    public void update(User user) {
        interfaceUser.save(user);
    }

    /**
     *
     * @param user
     */
    public void delete(User user) {
        interfaceUser.delete(user);
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean emailExists(String email) {
        Optional<User> usuario = interfaceUser.findByEmail(email);

        return !usuario.isEmpty();
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public Optional<User> authenticateUser(String email, String password) {
        return interfaceUser.findByEmailAndPassword(email, password);
    }

    /**
     *
     * @return
     */
    public Optional<User> lastUserId(){
        return interfaceUser.findTopByOrderByIdDesc();
    }

    /**
     *
     * @param monthBirthtDay
     * @return
     */
    public List<User> birthtDayList(String monthBirthtDay) {
        return interfaceUser.findByMonthBirthtDay(monthBirthtDay);
    }
}
