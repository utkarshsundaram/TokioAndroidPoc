package maxlife.maxlifepoc.network;

import android.content.Context;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is used to handle all request for API.
 *
 * @param <T> here you can pass your response model.
 * @author Utkarsh Sundaram
 */
public class NetworkRequest<T> implements Callback<T> {
    private static final String TAG = NetworkRequest.class.getSimpleName();

    private int mReqType;
    private Context mContext;

    public NetworkRequest(Context context, Call<T> call, int reqType) {

        mReqType = reqType;
        mContext = context;
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            if (response.body() != null) {
                // mUpdateListener.onUpdateView(response.body(), true, mReqType);
            } else {
                onFailure(call);
            }
        } else {
            onFailure(call);
        }
    }

    private void onFailure(Call<T> call) {
        Log.e(TAG, "onFailure: " + call.toString());
        //mUpdateListener.onUpdateView(call.toString(), false, mReqType);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e(TAG, "onFailure Throwable: " + call.toString());
        // mUpdateListener.onUpdateView(t.getMessage(), false, mReqType);
    }


    /**
     * Interface which is used to update UI after Network operation finish
     */
    public interface onUpdateListener {
        /**
         * Callback method called after Network Operation finish
         *
         * @param response Network response
         * @param success  response status type
         * @param reqType  request type
         */
        void onUpdateView(Object response, boolean success, int reqType);

    }
}
