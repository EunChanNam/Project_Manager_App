package cteam.cteamproject.domain.memberproject.relationrepository;

import cteam.cteamproject.domain.memberproject.Relation;

import java.util.List;

public interface RelationRepository {

    void addRelation(Long memberId, Long projectId);

    void removeRelation(Long memberId, Long projectId);

    List<Relation> findAll();
}
