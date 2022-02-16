package cteam.cteamproject.domain.member;

public enum Position {
    BackEnd("백엔드"),
    FrontEnd("프론트엔드"),
    Android("안드로이드"),
    IOS("IOS"),
    AI("인공지능");

    private final String description;

    Position(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
