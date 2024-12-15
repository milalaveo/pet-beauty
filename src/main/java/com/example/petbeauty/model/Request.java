package com.example.petbeauty.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Request {
    private int id;
    private int userId;
    private int serviceId;
    private String status;
    private LocalDate executionDate;
    private LocalTime executionTime;

    public Request(int id, int userId, int serviceId, String status, LocalDate executionDate, LocalTime executionTime) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.status = status;
        this.executionDate = executionDate;
        this.executionTime = executionTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public LocalTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(LocalTime executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id && userId == request.userId && serviceId == request.serviceId &&
                Objects.equals(status, request.status) &&
                Objects.equals(executionDate, request.executionDate) &&
                Objects.equals(executionTime, request.executionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, serviceId, status, executionDate, executionTime);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", userId=" + userId +
                ", serviceId=" + serviceId +
                ", status='" + status + '\'' +
                ", executionDate=" + executionDate +
                ", executionTime=" + executionTime +
                '}';
    }
}
