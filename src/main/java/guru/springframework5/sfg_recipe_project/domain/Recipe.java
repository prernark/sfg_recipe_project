package guru.springframework5.sfg_recipe_project.domain;

import guru.springframework5.sfg_recipe_project.enums.Difficulty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Id //indicating that id property is an id and will be persisted.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tells how to generate the Id value as an Identity
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob //on the db we store it as a BLoB
    private Byte[] image;
    //Below,1 Recipe has many Ingredients and cascade to make Recipe the owner.
    //mappedBy => Recipe stored in Ingredients as property c/as 'recipe'
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredientSet = new HashSet<>();
    @OneToOne(cascade= CascadeType.ALL) //Each recipe has one note and set up a cascade by making recipe the owner of that.
    private Notes notes;
    @ManyToMany
    @JoinTable (name="recipe_category",
                joinColumns = @JoinColumn (name="recipe_id"),
                inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categorySet = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Set<Ingredient> getIngredientSet() {
        return ingredientSet;
    }

    public void setIngredientSet(Set<Ingredient> ingredientSet) {
        this.ingredientSet = ingredientSet;
    }

    public Notes getNotes() {
        return notes;
    }

    //Dont just set Notes but ensure bidirectional save as well. So save recipe in the notes at the same time
    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    //Also when creating recipe, save the ingredients at the same time. Bidirectional taken care of
    public Recipe addIngredients(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredientSet.add(ingredient);
        return this;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }
}
