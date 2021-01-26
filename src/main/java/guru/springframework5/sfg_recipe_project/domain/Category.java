package guru.springframework5.sfg_recipe_project.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

//@Setter
//@Getter
@Data //Lombok annotation @Data takes care of getters, setters, toString, EqualsAndHashCode,RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"recipeSet"}) //above gives error due to bidirectional mapping getting circular. Hence exclude recipe from hashcode() n it works
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    //Need to give mappedBy so only 1 join table created. The String should be the property name in Recipe obj.
    @ManyToMany(mappedBy = "categorySet")
    private Set<Recipe> recipeSet;

}
