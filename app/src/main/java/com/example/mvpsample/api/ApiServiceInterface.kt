package com.example.mvpsample.api


import com.example.mvpsample.models.Championnat
import com.example.mvpsample.models.Equipe
import com.example.mvpsample.models.Joueur
import com.example.mvpsample.models.Joueurs
import com.example.mvpsample.utils.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

//    @GET("albums")
//    fun getAlbumList(): Observable<List<Album>>
//
//    @GET("albums/{id}")
//    fun getAlbum(@Path("id") id: Int): Observable<Album>
//
//    @DELETE("albums/{id}")
//    fun deleteAlbum(@Path("id") id: Int)
//
//    @GET("posts")
//    fun getPostList(): Observable<List<Post>>
//
//    @GET("posts/{id}")
//    fun getPost(@Path("id") id: Int): Observable<Post>
//
//    @DELETE("albums/{id}")
//    fun deletePost(@Path("id") id: Int)
//
//    @GET("users")
//    fun getUserList(): Observable<List<User>>
//
//    @GET("posts/{id}")
//    fun getUser(@Path("id") id: Int): Observable<User>
//
//    @DELETE("albums/{id}")
//    fun deleteUser(@Path("id") id: Int)
//

    //https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
    @GET("search_all_teams.php")
    fun getEquipesParChampionnat(@Query("l") leagueName: String): Observable<Championnat>


    //https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id=133604
    @GET("lookup_all_players.php")
    fun getJoueursParEquipe(@Query("id") teamId: String): Observable<Joueurs>





    /*
    In Kotlin, an interface can have a companion object but it is not part of the contract that must be implemented by
    classes that implement the interface. It is just an object associated to the interface that has one singleton
    instance. So it is a place you can store things, but doesn't mean anything to the implementation class.
     */
    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}