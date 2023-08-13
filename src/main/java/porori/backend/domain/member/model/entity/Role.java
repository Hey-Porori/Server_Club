package porori.backend.domain.member.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    MANAGER("manager"),
    MEMBER("member");

    private final String role;
}
