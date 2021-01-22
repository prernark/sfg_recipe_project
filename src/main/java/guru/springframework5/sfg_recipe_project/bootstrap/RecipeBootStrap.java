package guru.springframework5.sfg_recipe_project.bootstrap;

import guru.springframework5.sfg_recipe_project.domain.*;
import guru.springframework5.sfg_recipe_project.enums.Difficulty;
import guru.springframework5.sfg_recipe_project.repositories.CategoryRepository;
import guru.springframework5.sfg_recipe_project.repositories.RecipeRepository;
import guru.springframework5.sfg_recipe_project.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootStrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    //Load the classes with recipes and return a list
    public List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> uomTsp = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> uomTbsp = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> uomCup = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> uomPinch = unitOfMeasureRepository.findByDescription("Pinch");
        Optional<UnitOfMeasure> uomOunce = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> uomNumber = unitOfMeasureRepository.findByDescription("Number");
        Optional<UnitOfMeasure> uomPint = unitOfMeasureRepository.findByDescription("Pint");
        Optional<UnitOfMeasure> uomDash = unitOfMeasureRepository.findByDescription("Dash");
        //Doing it for just 2 but need to do for all of them
        if (uomDash.isEmpty()){
            throw new RuntimeException("Expected UOM not found");
        }
        if (!uomCup.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<Category> catgAmerican = categoryRepository.findByDescription("American");
        Optional<Category> catgMexican = categoryRepository.findByDescription("Mexican");
        if (!catgAmerican.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!\n" +
                "\n" +
                "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");

        Recipe guacRecipe = new Recipe();
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setNotes(guacNotes);
        guacRecipe.setPrepTime(10);
        guacRecipe.setServings(4);
        guacRecipe.setSource("Elise Bauer");
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#recipe159");
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

//Changed all below to call the new helper method addIngredients which takes care of bidirectional relationship and makes this code simpler
//        guacRecipe.getIngredientSet().add(new Ingredient("salt, more to taste", new BigDecimal(0.25), uomTsp.get(), guacRecipe));
        guacRecipe.addIngredients(new Ingredient("ripe avocados", new BigDecimal(2), uomNumber.get()));
        guacRecipe.addIngredients(new Ingredient("salt, more to taste", new BigDecimal(0.25), uomTsp.get()));
        guacRecipe.addIngredients(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), uomTbsp.get()));
        guacRecipe.addIngredients(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), uomTbsp.get()));
        guacRecipe.addIngredients(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), uomNumber.get()));
        guacRecipe.addIngredients(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), uomTbsp.get()));
        guacRecipe.addIngredients(new Ingredient("of freshly grated black pepper", new BigDecimal(1), uomDash.get()));
        guacRecipe.addIngredients(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), uomNumber.get()));
        guacRecipe.addIngredients(new Ingredient("Red radishes or jicama, to garnish", new BigDecimal(0), uomNumber.get()));
        guacRecipe.addIngredients(new Ingredient("Tortilla chips, to serve", new BigDecimal(0), uomNumber.get()));

        guacRecipe.getCategorySet().add(catgAmerican.get());
        guacRecipe.getCategorySet().add(catgMexican.get());

        //Below not needed after sorting out bidirectional save in Recipe
//        guacNotes.setRecipe(guacRecipe);

        //----------------------------------------------------------------------------------------------------------------------------------------------------------

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes! The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");

        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setCookTime(15);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setNotes(tacoNotes);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setServings(6);
        tacoRecipe.setSource("Sally Vargas");
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#recipe50182");
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

//Changed all below to call the new addIngredients() which takes care of bidirectional relationship and makes this code simpler
//        tacoRecipe.getIngredientSet().add(new Ingredient("ancho chili powder", new BigDecimal(2), uomTbsp.get()));
        tacoRecipe.addIngredients(new Ingredient("ancho chili powder", new BigDecimal(2), uomTbsp.get()));
        tacoRecipe.addIngredients(new Ingredient("dried oregano", new BigDecimal(1), uomTsp.get()));
        tacoRecipe.addIngredients(new Ingredient("dried cumin", new BigDecimal(1), uomTsp.get()));
        tacoRecipe.addIngredients(new Ingredient("'sugar'", new BigDecimal(1), uomTsp.get()));
        tacoRecipe.addIngredients(new Ingredient("salt", new BigDecimal(0.5), uomTsp.get()));
        tacoRecipe.addIngredients(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), uomNumber.get()));
        tacoRecipe.addIngredients(new Ingredient("finely grated orange zest", new BigDecimal(1), uomTsp.get()));
        tacoRecipe.addIngredients(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), uomTbsp.get()));
        tacoRecipe.addIngredients(new Ingredient("olive oil", new BigDecimal(2), uomTbsp.get()));
        tacoRecipe.addIngredients(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(6), uomNumber.get()));

        tacoRecipe.getCategorySet().add(catgAmerican.get());
        tacoRecipe.getCategorySet().add(catgMexican.get());

        //Below not needed after sorting out bidirectional save in Recipe
//        tacoNotes.setRecipe(tacoRecipe);

        recipes.add(guacRecipe);
        recipes.add(tacoRecipe);

        return recipes;
    }

}
