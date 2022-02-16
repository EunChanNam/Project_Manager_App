package cteam.cteamproject.domain.memberproject;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Relation {

    private final Long memberId;

    private final Long projectId;

    public Relation(Long memberId, Long projectId) {
        this.memberId = memberId;
        this.projectId = projectId;
    }
}
