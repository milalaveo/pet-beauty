package com.example.petbeauty.service;

import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Service;

import java.util.List;

public interface ServiceService {
    boolean addService(String name, String description, String price) throws ServiceException;
    boolean editService(int id, String name, String description, String price) throws ServiceException;
    boolean removeService(int id) throws ServiceException;
    List<Service> getAllServices() throws ServiceException;
    Service getServiceById(int id) throws ServiceException;
}
