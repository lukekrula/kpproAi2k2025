package cz.uhk.kppro.mapper;

import cz.uhk.kppro.dto.MemberDto;
import cz.uhk.kppro.model.Member;

public class MemberMapper {

    public static MemberDto toDto(Member member) {
        if (member == null) return null;

        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setEmail(member.getEmail());
        return dto;
    }
}
