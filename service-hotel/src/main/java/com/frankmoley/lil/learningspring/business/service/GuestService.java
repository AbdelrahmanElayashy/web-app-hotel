package com.frankmoley.lil.learningspring.business.service;

import com.frankmoley.lil.learningspring.data.entity.Guest;
import com.frankmoley.lil.learningspring.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getGuests() {
        List<Guest> guests = new ArrayList<>();
        Iterable<Guest> itr = guestRepository.findAll();
        itr.forEach(guest -> {
            guests.add(guest);
        });

        guests.sort(new Comparator<Guest>() {
            @Override
            public int compare(Guest o1, Guest o2) {
                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        });
        return guests;
    }
}
