package def.agoramain.project.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public enum ProjectAuth {
    ADMIN, USER
}
