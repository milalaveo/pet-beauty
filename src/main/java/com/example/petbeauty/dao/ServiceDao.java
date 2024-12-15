package com.example.petbeauty.dao;

import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Service;

import java.util.List;

public interface ServiceDao {
    boolean saveService(Service service) throws DaoException;
    boolean updateService(Service service) throws DaoException;
    boolean deleteService(int serviceId) throws DaoException;
    Service findServiceById(int serviceId) throws DaoException;
    List<Service> getServicesForUser(int userId) throws DaoException;
    List<Service> findAllServices() throws DaoException;
}

