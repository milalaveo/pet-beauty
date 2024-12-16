package com.example.petbeauty.service.impl;

import com.example.petbeauty.dao.ServiceDao;
import com.example.petbeauty.dao.impl.ServiceDaoImpl;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Service;
import com.example.petbeauty.service.ServiceService;

import java.math.BigDecimal;
import java.util.List;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceDao serviceDao = new ServiceDaoImpl();

    @Override
    public boolean addService(String name, String description, String price) throws ServiceException {
        try {
            BigDecimal parsedPrice = new BigDecimal(price);
            Service service = new Service(0, name, description, parsedPrice);
            return serviceDao.saveService(service);
        } catch (DaoException e) {
            throw new ServiceException("Error adding service", e);
        }
    }

    @Override
    public boolean editService(int id, String name, String description, String price) throws ServiceException {
        try {
            BigDecimal parsedPrice = new BigDecimal(price);
            Service service = new Service(id, name, description, parsedPrice);
            return serviceDao.updateService(service);
        } catch (DaoException e) {
            throw new ServiceException("Error updating service", e);
        }
    }

    @Override
    public boolean removeService(int id) throws ServiceException {
        try {
            return serviceDao.deleteService(id);
        } catch (DaoException e) {
            throw new ServiceException("Error deleting service", e);
        }
    }

    @Override
    public List<Service> getAllServices() throws ServiceException {
        try {
            return serviceDao.findAllServices();
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving all services", e);
        }
    }

    @Override
    public Service getServiceById(int id) throws ServiceException {
        try {
            return serviceDao.findServiceById(id);
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving service by ID", e);
        }
    }
}
