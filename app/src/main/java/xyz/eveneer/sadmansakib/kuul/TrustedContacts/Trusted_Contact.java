package xyz.eveneer.sadmansakib.kuul.TrustedContacts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.eveneer.sadmansakib.kuul.Data.Dao.PhoneNumberDao;
import xyz.eveneer.sadmansakib.kuul.Data.DataBase.PhoneNumberRoomDatabase;
import xyz.eveneer.sadmansakib.kuul.Kuul;
import xyz.eveneer.sadmansakib.kuul.Models.AddContacts;
import xyz.eveneer.sadmansakib.kuul.Models.ContactsHolder;
import xyz.eveneer.sadmansakib.kuul.Models.TrustedContacts;
import xyz.eveneer.sadmansakib.kuul.R;

public class Trusted_Contact extends AppCompatActivity {

    static ArrayList<ContactsHolder> trusted_people;
    private final String TAG = this.getClass().getSimpleName();
    PhoneNumberRoomDatabase phoneDB;
    ContactAdapter adapter;
    private LiveData<String> number;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted__contact);
        phoneDB = PhoneNumberRoomDatabase.getDatabase(this);
        PhoneNumberDao phoneDao = phoneDB.phoneNumberDao();
        number = phoneDao.getUserNumber();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        trusted_people = new ArrayList<>();


        /*method for fetching trusted contacts from server**/
        showContacts();

        adapter = new ContactAdapter(trusted_people);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider)));
        recyclerView.addItemDecoration(itemDecorator);
    }

    private void showContacts() {
        Call<TrustedContacts> call = Kuul.getClient().showContacts(number.getValue());
        call.enqueue(new Callback<TrustedContacts>() {
            @Override
            public void onResponse(@NonNull Call<TrustedContacts> call, @NonNull Response<TrustedContacts> response) {
                assert response.body() != null;
                ContactsHolder contactsHolder = new ContactsHolder(
                        response.body().getName(), response.body().getPhone()
                );
                trusted_people.add(contactsHolder);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<TrustedContacts> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }

    public void addMyContacts(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        @SuppressLint("InflateParams") final View number_input_form = layoutInflater.inflate(R.layout.number_input_form, null);
        EditText name = number_input_form.findViewById(R.id.input_name);
        EditText contact_number = number_input_form.findViewById(R.id.input_number);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setView(number_input_form)
                .setPositiveButton("ADD CONTACT", (dialog1, which) ->
                        sendContactsToServer(name.getText().toString(), contact_number.getText().toString()))
                .setNeutralButton("CANCEL", (dialog1, which) -> {
                    if (dialog1 != null) {
                        dialog1.dismiss();
                    }
                });
        dialog.setCancelable(true);
        dialog.create();
        dialog.show();

    }

    private void sendContactsToServer(String name, String contact_number) {
        Call<AddContacts> call = Kuul.getClient().addContacts(number.getValue(), name, contact_number);
        call.enqueue(new Callback<AddContacts>() {
            @Override
            public void onResponse(@NonNull Call<AddContacts> call, @NonNull Response<AddContacts> response) {
                assert response.body() != null;
                if (response.body().getStatus().contains("success")) {
                    Toast.makeText(getApplicationContext(), "Contact added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddContacts> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }
}
