package guru.springframework5.sfg_recipe_project.domain;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne //Here we do not need to cascade as we are making Recipe the owner as if we delete notes we dont want to delete Recipe
    private Recipe recipe;
    @Lob //String has its size. Notes could be larger hence @Lob (Large Obj) and stored as CLoB since a String.
    private String recipeNotes;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
