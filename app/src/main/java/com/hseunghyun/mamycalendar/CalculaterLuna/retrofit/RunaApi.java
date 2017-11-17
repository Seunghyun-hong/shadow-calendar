package com.hseunghyun.mamycalendar.CalculaterLuna.retrofit;


import com.hseunghyun.mamycalendar.CalculaterLuna.model.Runa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Thunder on 2017-11-01.
 * 쏠라데이를 받아서 룬데이로 바꿔주는 데이터를 만들어 보자.
 */

public interface RunaApi {
    String BASE_URL = "http://apis.data.go.kr/B090041/openapi/service/LrsrCldInfoService/";
    String APP_ID = "8GCtCtVkRWZEDetec2si8Jxzc38BCUEvCrgnZr03jgzyS9GfVpbu4DuFY%2FtBb3Z7mdhnOi7yobKCoWTa64swMQ%3D%3D";

    @GET("getLunCalInfo?_type=json&ServiceKey=" + APP_ID)
    Call<Runa> getCaculaterDay(@Query("solYear") String solYear,
                            @Query("solMonth") String solMonth,
                            @Query("solDay") String solDay);
}

class lunaUtil {
//    TaskService taskService = ServiceGenerator.createService(TaskService.class);
//    Call<List<Task>> call = taskService.getTasks();
//    List<Task>> tasks = call.execute().body();

//    public interface TaskService {
//        @GET("/tasks")
//        Call<List<Task>> getTasks();
//    }

//    GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
//    Call<List<Contributor>> call = gitHubService.repoContributors("square", "retrofit");



}