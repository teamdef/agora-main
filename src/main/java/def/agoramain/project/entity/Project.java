package def.agoramain.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String thumbnail;
    @NotNull
    private String title;
    @NotNull
    private String description;

    @Builder
    public Project(String thumbnail, String title, String description) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
    }
}
