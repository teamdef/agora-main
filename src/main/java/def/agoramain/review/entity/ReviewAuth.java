package def.agoramain.review.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public enum ReviewAuth {
    ADMIN, USER
}
