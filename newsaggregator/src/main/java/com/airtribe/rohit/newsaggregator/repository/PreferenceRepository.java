package com.airtribe.rohit.newsaggregator.repository;

import com.airtribe.rohit.newsaggregator.user.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
}
