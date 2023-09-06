package ru.gmotrich.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gmotrich.reddit.model.Subreddit;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Optional<Subreddit> findByName(String subredditName);

}
