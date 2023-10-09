package com.example.ead_frontend_android.Response;

public class TrainScheduleResponse {


    private String id;
    private String trainName;
    private String scheduleDateTime;
    private int seatsCount;
    private String from;
    private String to;

    public TrainScheduleResponse(String id, String trainName, String scheduleDateTime, int seatsCount, String from, String to) {
        this.id = id;
        this.trainName = trainName;
        this.scheduleDateTime = scheduleDateTime;
        this.seatsCount = seatsCount;
        this.from = from;
        this.to = to;
    }

    public TrainScheduleResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
