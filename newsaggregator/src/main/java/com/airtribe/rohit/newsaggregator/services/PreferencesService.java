package com.airtribe.rohit.newsaggregator.services;

import com.airtribe.rohit.newsaggregator.repository.PreferenceRepository;
import com.airtribe.rohit.newsaggregator.user.Preference;
import com.airtribe.rohit.newsaggregator.user.User;
import com.airtribe.rohit.newsaggregator.user.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PreferencesService {
    private final PreferenceRepository preferenceRepository;
    private final UserService userService;

    public PreferencesService(PreferenceRepository preferenceRepository, UserService userService) {
        this.preferenceRepository = preferenceRepository;
        this.userService = userService;
    }

    public List<Preference> getPreferences(){
        return preferenceRepository.findAll();
    }
    public Set<Preference> getPreferencesByUsername(String username){
        return  userService.getUserByName(username).get().getPreferences();
    }

    public Optional<Preference> createPreference(Preference preference){
        return Optional.of(preferenceRepository.save(preference));
    }

    public Set<Preference> setPreferenceForUser(String username,List<Long>  preferenceIds) throws Exception{
        List<Preference> preference = preferenceRepository.findAllById(preferenceIds);
        Set<Preference> sets = new HashSet<>();
        sets.addAll(preference);
        Optional<User> user = userService.getUserByName(username);
        if(user.isPresent()){
          user.get().setPreferences(sets);


            return userService.updateUser(user).getPreferences();
        }
        throw new Exception("something went wrong");
    }


}
