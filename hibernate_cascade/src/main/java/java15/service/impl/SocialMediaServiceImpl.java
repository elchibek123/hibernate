package java15.service.impl;

import java15.dao.SocialMediaDao;
import java15.entities.SocialMedia;
import java15.service.SocialMediaService;

import java.util.Optional;

public class SocialMediaServiceImpl implements SocialMediaService {
    private final SocialMediaDao socialMediaDao;

    public SocialMediaServiceImpl(SocialMediaDao socialMediaDao) {
        this.socialMediaDao = socialMediaDao;
    }

    @Override
    public String createSocialMedia(SocialMedia socialMedia) {
        return socialMediaDao.save(socialMedia);
    }

    @Override
    public Optional<SocialMedia> getSocialMediaById(Long id) {
        return socialMediaDao.findById(id);
    }

    @Override
    public void deleteSocialMedia(SocialMedia socialMedia) {
        socialMediaDao.delete(socialMedia);
    }

    @Override
    public void assignSocialMediaToPerson(Long id, Long personId) {
        socialMediaDao.assignSocialMediaToPerson(id, personId);
    }
}
