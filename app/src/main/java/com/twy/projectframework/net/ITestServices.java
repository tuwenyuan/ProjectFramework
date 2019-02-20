package com.twy.projectframework.net;

import com.twy.network.business.Observable;
import com.twy.network.interfaces.FileType;
import com.twy.network.interfaces.GET;
import com.twy.network.interfaces.Headers;
import com.twy.network.interfaces.Multipart;
import com.twy.network.interfaces.POST;
import com.twy.network.interfaces.Query;
import com.twy.projectframework.CommBean;
import com.twy.projectframework.User;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/1/11.
 * PS: Not easy to write code, please indicate.
 */
public interface ITestServices {
    @Headers({"header1:header1value","header2:header2value"})
    @POST("servlet/GetUser")
    Observable<User> getUserPost(@Query(value = "userName", encoded = true) String userName, @Query("password") String password);
    @Headers({"header1:header1value","header2:header2value"})
    @GET("servlet/GetUser")
    Observable<User> getUserGet(@Query(value = "userName", encoded = true) String userName, @Query("password") String password);
    @Multipart
    @POST("servlet/UploadFile")
    Observable<CommBean> uploadFile(@Query(value = "params", encoded = true) String params, @FileType java.io.File file);
}
