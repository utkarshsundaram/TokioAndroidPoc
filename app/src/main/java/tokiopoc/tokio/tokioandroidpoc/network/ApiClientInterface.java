package tokiopoc.tokio.tokioandroidpoc.network;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tokiopoc.tokio.tokioandroidpoc.model.BaseResponseModel;

public interface ApiClientInterface {

    @POST("")
    Call<BaseResponseModel> sendDetails(@Body Object object);

}
