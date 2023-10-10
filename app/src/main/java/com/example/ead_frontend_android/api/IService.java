package com.example.ead_frontend_android.api;

import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.Response.UserResponse;
import com.example.ead_frontend_android.model.Reservation;
import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IService {

    @GET("/api/trains")
    Call<List<TrainScheduleResponse>> getTrainSchedule();

    @GET("/api/reservation")
    Call<List<ReservationResponse>> getAllReservation();

    @GET("/bookings/user/{id}")
    Call<List<ReservationResponse>> getUserReservation(@Path("id") String id);

    @POST("/api/reservation")
    Call<ReservationResponse> addReservation(@Body Reservation reservation);

    @PUT("api/reservation/{id}")
    Call<ReservationResponse> EditReservation(@Path("id") String id, @Body Reservation reservation);


    @DELETE("api/reservation/{id}")
    Call<Void> cancelReservation(@Path("id") String reservationId);

    // user get

    @GET("api/user")
    Call<UserResponse> getUser(@Path("id") String id, @Body User user);



    // user update
    @PUT("api/user/{id}")
    Call<UserResponse> UpdateUser(@Path("id") String id, @Body User user);


    // signup
    @POST("/api/user")
    Call<UserResponse> signup (@Body User user);
    // login
    @POST("/api/user/login")
    Call<UserResponse> login (@Body User user);



}
