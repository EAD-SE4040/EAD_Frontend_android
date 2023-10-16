package com.example.ead_frontend_android.Response;

public class ReservationResponse {


    private String id;
    private String UserID;
    private String trainID;
    private String reservationDate;
    private int noOfSeates;
    private boolean status;

    private String nic;

    private String trainName;



    public ReservationResponse() {
    }

    public ReservationResponse(String id, String userID, String trainID, String reservationDate, int noOfSeates, boolean status, String nic, String trainName) {
        this.id = id;
        this.UserID = userID;
        this.trainID = trainID;
        this.reservationDate = reservationDate;
        this.noOfSeates = noOfSeates;
        this.status = status;
        this.nic = nic;
        this.trainName = trainName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNoOfSeates() {
        return noOfSeates;
    }

    public void setNoOfSeates(int noOfSeates) {
        this.noOfSeates = noOfSeates;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
