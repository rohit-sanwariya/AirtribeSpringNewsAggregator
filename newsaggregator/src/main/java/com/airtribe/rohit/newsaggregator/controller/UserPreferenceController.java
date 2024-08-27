package com.airtribe.rohit.newsaggregator.controller;

import com.airtribe.rohit.newsaggregator.repository.PreferenceRepository;
import com.airtribe.rohit.newsaggregator.services.PreferencesService;
import com.airtribe.rohit.newsaggregator.user.Preference;
import com.airtribe.rohit.newsaggregator.user.User;
import com.airtribe.rohit.newsaggregator.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferencesService preferencesService;


    @GetMapping
    public ResponseEntity<Set<Preference>> getPreferences(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        return ResponseEntity.ok(user.getPreferences());
    }

    @GetMapping("/create/{name}")
    public ResponseEntity<Preference> createPreferences(@PathVariable("name") String name) {
        Preference preference = new Preference();
        preference.setName(name);

        return ResponseEntity.ok(preferencesService.createPreference(preference).get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Preference>> getAllPreferences() {
        return ResponseEntity.ok(preferencesService.getPreferences());
    }


    @PutMapping("/update")
    public ResponseEntity<Set<Preference>> updatePreferences(
            Authentication authentication,
            @RequestBody List<Long> preferenceIds) throws Exception {
        String username = authentication.getName();

        return ResponseEntity.ok(preferencesService.setPreferenceForUser(username, preferenceIds));
    }
}
