package cz.uhk.kppro.service;


import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.User;

public interface MemberService {

    Member createForUser(User user);

    Member findById(long id);
}

