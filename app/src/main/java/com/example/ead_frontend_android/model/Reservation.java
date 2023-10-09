package com.example.ead_frontend_android.model;

public class Reservation {


    private String UserID;
    private String TrainID;
    private String ReservationDate;
    private int NoOfSeates;
    private boolean Status;

    public Reservation() {
    }

    public Reservation(String userID, String trainID, String reservationDate, int noOfSeates, boolean status) {
        UserID = userID;
        TrainID = trainID;
        ReservationDate = reservationDate;
        NoOfSeates = noOfSeates;
        Status = status;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTrainID() {
        return TrainID;
    }

    public void setTrainID(String trainID) {
        TrainID = trainID;
    }

    public String getReservationDate() {
        return ReservationDate;
    }

    public void setReservationDate(String reservationDate) {
        ReservationDate = reservationDate;
    }

    public int getNoOfSeates() {
        return NoOfSeates;
    }

    public void setNoOfSeates(int noOfSeates) {
        NoOfSeates = noOfSeates;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
