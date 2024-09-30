package com.kare.contactsbackend.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("local")
@Profile("development")
public class LocalFileService extends BaseFileService {

}
