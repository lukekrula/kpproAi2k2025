ALTER TABLE member_community DROP CONSTRAINT FKJGXB8X9XV4H9Q8QXP9G2SAABI;

ALTER TABLE member_community
    ADD CONSTRAINT fk_member_community_communities
        FOREIGN KEY (community_id)
            REFERENCES communities(id);
