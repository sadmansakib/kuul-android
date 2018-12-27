package xyz.eveneer.sadmansakib.kuul.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.eveneer.sadmansakib.kuul.R;
import xyz.eveneer.sadmansakib.kuul.UI.CustomPrompt;
import xyz.eveneer.sadmansakib.kuul.report.report_incident;

/**
 * A simple {@link Fragment} subclass.
 */
public class help extends Fragment implements View.OnClickListener {

//    protected Location mLastLocation;
    AppCompatButton help_button, previous_report_button;
    private double lat;
    private double longi;
//    private FusedLocationProviderClient mFusedLocationProviderClient;

    public help() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        help_button = view.findViewById(R.id.help_btn);
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        previous_report_button = view.findViewById(R.id.previous_report_btn);
        help_button.setOnClickListener(this);
        previous_report_button.setOnClickListener(this);
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
//        getLastLocation();
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.help_btn) {
            CustomPrompt customPrompt= new CustomPrompt(getActivity());
            customPrompt.show();
//            final String loc = lat+","+longi;
//            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
//                @Override
//                public void onSuccess(Account account) {
//                    Call<sos>call = Kuul.getClient().sendSOS(account.getPhoneNumber().toString(),loc);
//                    call.enqueue(new Callback<sos>() {
//                        @Override
//                        public void onResponse(@NonNull Call<sos> call, @NonNull Response<sos> response) {
//                            assert response.body() != null;
//                            if(response.body().getStatus().contains("success")){
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<sos> call, Throwable t) {
//
//                        }
//                    });
//                }
//
//                @Override
//                public void onError(AccountKitError accountKitError) {
//
//                }
//            });
        }
        else {
            startActivity(new Intent(getActivity(), report_incident.class));
        }
    }
}
