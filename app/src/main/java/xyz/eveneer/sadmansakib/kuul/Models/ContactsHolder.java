package xyz.eveneer.sadmansakib.kuul.Models;

import androidx.annotation.NonNull;

public class ContactsHolder {
    @NonNull
    private String name;
    @NonNull
    private String number;
    private boolean isSelected = false;

    public ContactsHolder(@NonNull String name, @NonNull String number) {
        this.name = name;
        this.number = number;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getPhone() {
        return number;
    }
}
