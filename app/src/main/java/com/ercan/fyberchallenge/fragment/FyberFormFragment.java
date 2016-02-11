package com.ercan.fyberchallenge.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ercan.fyberchallenge.R;
import com.ercan.fyberchallenge.data.model.RequestParam;
import com.ercan.fyberchallenge.data.model.rest.ResponseEnvelope;
import com.ercan.fyberchallenge.data.rest.RestClient;
import com.ercan.fyberchallenge.util.CommonUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ercan on 2/10/2016.
 */
public class FyberFormFragment extends Fragment {

    public static final String TAG = "FyberFormFragment";

    @Bind(R.id.edtUID)
    EditText edtUID;
    @Bind(R.id.edtApiKey)
    EditText edtApiKey;
    @Bind(R.id.edtAppId)
    EditText edtAppId;
    @Bind(R.id.edtPub0)
    EditText edtPub0;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    View rootView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private OnSubmit onSubmitListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSubmit) {
            onSubmitListener = (OnSubmit) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_fyberform, container, false);
        ButterKnife.bind(this, rootView);
        edtApiKey.setText("1c915e3b5d42d05136185030892fbb846c278927");
        edtAppId.setText("2070");
        edtUID.setText("player2");
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        btnSubmit.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);

        List<RequestParam> params = new ArrayList();
        String apiKey;

        if (!TextUtils.isEmpty(edtAppId.getText().toString())) {
            params.add(new RequestParam("appid", edtAppId.getText().toString()));
        }
        if (!TextUtils.isEmpty(edtUID.getText().toString())) {
            params.add(new RequestParam("uid", edtUID.getText().toString()));
        }
        if (!TextUtils.isEmpty(edtPub0.getText().toString())) {
            params.add(new RequestParam("pub0", edtPub0.getText().toString()));
        }
        if (TextUtils.isEmpty(edtApiKey.getText().toString())) {
            Toast.makeText(getContext(), R.string.warn_input_apikey, Toast.LENGTH_SHORT).show();
            return;
        } else {
            apiKey = edtApiKey.getText().toString();
        }

        params.add(new RequestParam("ip", "109.235.143.113"));
        params.add(new RequestParam("locale", "de"));
        params.add(new RequestParam("device_id", "2b6f0cc904d137be2e1730235f5664094b831186"));
        params.add(new RequestParam("timestamp", CommonUtils.getUnixTimeStamp()));

        RestClient.getInstance().getOffers(params, apiKey, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), R.string.warn_get_offers_failed, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btnSubmit.setEnabled(true);
                    }
                });

            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    if (onSubmitListener != null) {
                        final ResponseEnvelope responseEnvelope = new Gson().fromJson(response.body().string(), ResponseEnvelope.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onSubmitListener.onSubmit(responseEnvelope);
                            }
                        });

                    }

                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), R.string.warn_get_offers_failed, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        btnSubmit.setEnabled(true);
                    }
                });


            }
        });

    }
}
