package com.example.petbeauty.service.impl;

import com.example.petbeauty.dao.RequestDao;
import com.example.petbeauty.dao.impl.RequestDaoImpl;
import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Request;
import com.example.petbeauty.service.RequestService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDao = new RequestDaoImpl();

    @Override
    public boolean createRequest(int userId, int serviceId, String status, String executionDate, String executionTime) throws ServiceException {
        try {
            LocalDate parsedDate;
            LocalTime parsedTime;

            try {
                parsedDate = LocalDate.parse(executionDate);
            } catch (DateTimeParseException e) {
                throw new ServiceException("Invalid date format. Expected format: yyyy-MM-dd.", e);
            }

            try {
                parsedTime = LocalTime.parse(executionTime);
            } catch (DateTimeParseException e) {
                throw new ServiceException("Invalid time format. Expected format: HH:mm.", e);
            }

            Request request = new Request(0, userId, serviceId, status, parsedDate, parsedTime);
            return requestDao.saveRequest(request);
        } catch (DateTimeParseException e) {
            throw new ServiceException("Invalid date", e);
        } catch (DaoException e) {
            throw new ServiceException("Error creating request", e);
        }
    }

    @Override
    public boolean editRequest(int id, int userId, int serviceId, String status, String executionDate, String executionTime) throws ServiceException {
        try {
            LocalDate parsedDate;
            LocalTime parsedTime;

            try {
                parsedDate = LocalDate.parse(executionDate);
            } catch (DateTimeParseException e) {
                throw new ServiceException("Invalid date format. Expected format: yyyy-MM-dd.", e);
            }

            try {
                parsedTime = LocalTime.parse(executionTime);
            } catch (DateTimeParseException e) {
                throw new ServiceException("Invalid time format. Expected format: HH:mm.", e);
            }

            Request request = new Request(id, userId, serviceId, status, parsedDate, parsedTime);
            return requestDao.updateRequest(request);
        } catch (DaoException e) {
            throw new ServiceException("Error editing request", e);
        }
    }

    @Override
    public boolean deleteRequest(int requestId) throws ServiceException {
        try {
            return requestDao.deleteRequest(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Error deleting request", e);
        }
    }

    @Override
    public Request getRequestById(int requestId) throws ServiceException {
        try {
            return requestDao.findRequestById(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving request", e);
        }
    }

    @Override
    public List<Request> getRequestsByUserId(int userId) throws ServiceException {
        try {
            return requestDao.findRequestsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving requests", e);
        }
    }
}
