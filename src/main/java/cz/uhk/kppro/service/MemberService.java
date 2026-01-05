package cz.uhk.kppro.service;


import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.User;

import java.util.List;

public interface MemberService {

    Member createForUser(User user);

    Member findById(long id);

    List<Member> findByCommunity(long communityId);

    Member findByUserId(long userId);
}

