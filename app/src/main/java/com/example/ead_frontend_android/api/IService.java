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



    @POST("/api/reservation")
    Call<ReservationResponse> addReservation(@Body Reservation reservation);



    @PUT("api/Reservation/{id}")
    Call<ReservationResponse> EditReservation(@Path("id") String id, @Body Reservation reservation);



    @DELETE("api/reservation/{id}")
    Call<Void> deleteReservation(@Path("id") String reservationId);



    // user

    // user get

    @GET("api/Reservation/{id}")
    Call<UserResponse> getUser(@Path("id") String id, @Body User user);


    // user update
    @PUT("api/user/{id}")
    Call<UserResponse> UpdateUser(@Path("id") String id, @Body User user);

    // login


    // signup

    @POST("/api/user")
    Call<UserResponse> addReservation(@Body User user);
}
