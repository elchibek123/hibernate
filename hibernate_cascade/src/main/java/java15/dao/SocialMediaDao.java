package java15.dao;

import java15.entities.SocialMedia;

import java.util.Optional;

public interface SocialMediaDao {
    String save(SocialMedia socialMedia);

    Optional<SocialMedia> findById(Long id);

    void delete(SocialMedia socialMedia);

    void assignSocialMediaToPerson(Long id, Long personId);
}
