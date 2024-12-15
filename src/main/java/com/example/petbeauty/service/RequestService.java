package com.example.petbeauty.service;

import com.example.petbeauty.exception.ServiceException;
import com.example.petbeauty.model.Request;

import java.util.List;

public interface RequestService {
    boolean createRequest(int userId, int serviceId, String status, String executionDate, String executionTime) throws ServiceException;
    boolean editRequest(int id, int serviceId, String status, String executionDate, String executionTime) throws ServiceException;
    boolean deleteRequest(int requestId) throws ServiceException;
    Request getRequestById(int requestId) throws ServiceException;
    List<Request> getRequestsByUserId(int userId) throws ServiceException;
}
