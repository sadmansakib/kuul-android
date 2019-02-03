package xyz.eveneer.sadmansakib.kuul.TrustedContacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import xyz.eveneer.sadmansakib.kuul.Models.ContactsHolder;
import xyz.eveneer.sadmansakib.kuul.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<ContactsHolder> cont;
    private ContactsHolder list;

    public ContactAdapter(ArrayList<ContactsHolder> cont) {
        this.cont = cont;
    }

    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        list = cont.get(position);
        holder.title.setText(list.getName());
        holder.phone.setText(list.getPhone());
    }

    @Override
    public int getItemCount() {
        return cont == null ? 0 : cont.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, phone;
        LinearLayout contact_select_layout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.contact_name);
            phone = itemView.findViewById(R.id.contact_phone);
            contact_select_layout = itemView.findViewById(R.id.contact_select_layout);
        }
    }
}
