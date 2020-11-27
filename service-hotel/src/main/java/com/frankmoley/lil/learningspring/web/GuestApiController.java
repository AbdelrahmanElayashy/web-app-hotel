package com.frankmoley.lil.learningspring.web;

import com.frankmoley.landon.aop.Timed;
import com.frankmoley.lil.learningspring.business.service.GuestService;
import com.frankmoley.lil.learningspring.data.entity.Guest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestApiController {

    private GuestService guestService;

    public GuestApiController(GuestService guestService) {
        this.guestService = guestService;
    }

    // Timed new Configruation to turn on logging
    @GetMapping("/guests")
    @Timed
    public List<Guest> getGuests() {
        return guestService.getGuests();
    }
}
