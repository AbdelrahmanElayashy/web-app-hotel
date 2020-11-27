package com.frankmoley.lil.learningspring.web;

import com.frankmoley.lil.learningspring.business.service.GuestService;
import com.frankmoley.lil.learningspring.data.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestWebController {
    private final GuestService guestService;

    @Autowired
    public GuestWebController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String printGuests(Model model) {
        List<Guest> guests = guestService.getGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
