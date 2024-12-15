package com.example.petbeauty.dao;

import com.example.petbeauty.exception.DaoException;
import com.example.petbeauty.model.Request;

import java.util.List;

public interface RequestDao {
    boolean saveRequest(Request request) throws DaoException;
    boolean updateRequest(Request request) throws DaoException;
    boolean deleteRequest(int requestId) throws DaoException;
    Request findRequestById(int requestId) throws DaoException;
    List<Request> findRequestsByUserId(int userId) throws DaoException;
}
