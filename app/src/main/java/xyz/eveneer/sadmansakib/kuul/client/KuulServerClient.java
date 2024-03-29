package xyz.eveneer.sadmansakib.kuul.client;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.eveneer.sadmansakib.kuul.model.Register;
import xyz.eveneer.sadmansakib.kuul.model.UserExistanceCheck;
import xyz.eveneer.sadmansakib.kuul.model.sos;

public interface KuulServerClient {
    @FormUrlEncoded
    @POST("user-exists")
    Call<UserExistanceCheck> checkUser(
            @Field(value = "phone") String phone_number
    );

    @FormUrlEncoded
    @POST("register")
    Call<Register> registerUser(
            @Field(value = "name") String name,
            @Field(value = "phone") String phone_number,
            @Field(value = "gender") String gender,
            @Field(value = "address") String address
    );

    @FormUrlEncoded
    @POST("SOS")
    Call<sos> sendSOS(
            @Field(value = "phone") String phone_number,
            @Field(value = "location") String location
    );
}
