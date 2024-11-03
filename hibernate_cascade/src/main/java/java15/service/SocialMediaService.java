package java15.service;

import java15.entities.SocialMedia;

import java.util.Optional;

public interface SocialMediaService {
    String createSocialMedia(SocialMedia socialMedia);

    Optional<SocialMedia> getSocialMediaById(Long id);

    void deleteSocialMedia(SocialMedia socialMedia);

    void assignSocialMediaToPerson(Long id, Long personId);
}
