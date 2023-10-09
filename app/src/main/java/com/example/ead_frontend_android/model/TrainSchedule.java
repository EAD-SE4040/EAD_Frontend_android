package com.example.ead_frontend_android.model;

public class TrainSchedule {
    private String trainName;
    private String scheduleDateTime;
    private int seatsCount;
    private String from;
    private String to;

    public TrainSchedule(String trainName, String scheduleDateTime, int seatsCount, String from, String to) {
        this.trainName = trainName;
        this.scheduleDateTime = scheduleDateTime;
        this.seatsCount = seatsCount;
        this.from = from;
        this.to = to;
    }

    public TrainSchedule() {
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(String scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
