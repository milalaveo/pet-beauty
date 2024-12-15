package com.example.petbeauty.service.impl;

import com.example.petbeauty.dao.RequestDao;
import com.example.petbeauty.dao.impl.RequestDaoImpl;
import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Request;
import com.example.petbeauty.service.RequestService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDao = new RequestDaoImpl();

    @Override
    public boolean createRequest(int userId, int serviceId, String status, String executionDate, String executionTime) throws ServiceException {
        try {
            Request request = new Request(0, userId, serviceId, status, LocalDate.parse(executionDate), LocalTime.parse(executionTime));
            return requestDao.saveRequest(request);
        } catch (Exception e) {
            throw new ServiceException("Error creating request", e);
        }
    }

    @Override
    public boolean editRequest(int id, int serviceId, String status, String executionDate, String executionTime) throws ServiceException {
        try {
            Request request = new Request(id, 0, serviceId, status, LocalDate.parse(executionDate), LocalTime.parse(executionTime));
            return requestDao.updateRequest(request);
        } catch (Exception e) {
            throw new ServiceException("Error editing request", e);
        }
    }

    @Override
    public boolean deleteRequest(int requestId) throws ServiceException {
        try {
            return requestDao.deleteRequest(requestId);
        } catch (Exception e) {
            throw new ServiceException("Error deleting request", e);
        }
    }

    @Override
    public Request getRequestById(int requestId) throws ServiceException {
        try {
            return requestDao.findRequestById(requestId);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving request", e);
        }
    }

    @Override
    public List<Request> getRequestsByUserId(int userId) throws ServiceException {
        try {
            return requestDao.findRequestsByUserId(userId);
        } catch (Exception e) {
            throw new ServiceException("Error retrieving requests", e);
        }
    }
}
