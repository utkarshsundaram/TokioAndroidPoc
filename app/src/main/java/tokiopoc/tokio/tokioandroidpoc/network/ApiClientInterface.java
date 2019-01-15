package maxlife.maxlifepoc.network;


import maxlife.maxlifepoc.model.BaseResponseModel;
import maxlife.maxlifepoc.utils.ApiConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiClientInterface {

    @POST(ApiConstants.EMAIL_SEND)
    Call<BaseResponseModel> sendDetails(@Body Object object);

}
