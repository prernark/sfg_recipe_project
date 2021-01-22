INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');
INSERT INTO unit_of_measure (description) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description) VALUES ('Cup');
INSERT INTO unit_of_measure (description) VALUES ('Pinch');
INSERT INTO unit_of_measure (description) VALUES ('Ounce');
INSERT INTO unit_of_measure (description) VALUES ('Number');
INSERT INTO unit_of_measure (description) VALUES ('Pint');
INSERT INTO unit_of_measure (description) VALUES ('Dash');

/*---------------------------------------------------------------------------------------------------------------*/

INSERT INTO notes (recipe_Notes) VALUES ('Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!

Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don’t let the lack of availability of other ingredients stop you from making guacamole.
Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.
Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.');

INSERT INTO recipe(description, prep_time, cook_time, servings, source, url, difficulty, image, directions, notes_id )
VALUES ('Perfect Guacamole',10, 0, 4,'Elise Bauer',
         'https://www.simplyrecipes.com/recipes/perfect_guacamole/#recipe159','EASY', '',
         '1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.

2 Mash with a fork: Using a fork, roughly mash the avocado. (Don''t overdo it! The guacamole should be a little chunky.)

3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.

Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.

Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.

Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.

4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.',1);

UPDATE notes SET recipe_id = 1 where id = 1;

INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('ripe avocados', 2, 6, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('salt, more to taste', 0.25, 1, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('fresh lime juice or lemon juice', 1, 2, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('minced red onion or thinly sliced green onion', 2, 2, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('serrano chiles, stems and seeds removed, minced', 2, 6, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('cilantro (leaves and tender stems), finely chopped', 2, 2, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('of freshly grated black pepper', 1, 8, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('ripe tomato, seeds and pulp removed, chopped', 0.5, 6, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('Red radishes or jicama, to garnish', 0, 6, 1);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('Tortilla chips, to serve', 0, 6, 1);

INSERT INTO recipe_category VALUES (1,3);
INSERT INTO recipe_category VALUES (1,1);
/*---------------------------------------------------------------------------------------------------------------------------------*/

INSERT INTO notes (recipe_notes) VALUES ('We have a family motto and it is this: Everything goes better in a tortilla.

Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.
Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!

First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.

Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes! The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.

I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.

Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.

You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!');

INSERT INTO recipe(description, prep_time, cook_time, servings, source, url, difficulty, image, directions, notes_id )
VALUES ('Spicy Grilled Chicken Tacos',20, 15, 6,'Sally Vargas',
         'https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#recipe50182','MODERATE', '',
         '1 Prepare a gas or charcoal grill for medium-high, direct heat.

2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.

Set aside to marinate while the grill heats and you prepare the rest of the toppings.

3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.

4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.

Wrap warmed tortillas in a tea towel to keep them warm until serving.

5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.', 2);

UPDATE notes SET recipe_id = 2 where id = 2;

INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('ancho chili powder', 2, 2, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('dried oregano', 1, 1, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('dried cumin', 1, 1, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('sugar', 1, 1, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('salt', 0.5, 1, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('clove garlic, finely chopped', 1, 6, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('finely grated orange zest', 1, 2, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('fresh-squeezed orange juice', 3, 2, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('olive oil', 2, 2, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('skinless, boneless chicken thighs (1 1/4 pounds)', 6, 6, 2);

INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('small corn tortillas', 8, 6, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('packed baby arugula (3 ounces)', 3, 3, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('medium ripe avocados, sliced', 2, 6, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('radishes, thinly sliced', 4, 6, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('cherry tomatoes, halved', 0.5, 7, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('red onion, thinly sliced', 0.25, 6, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('Roughly chopped cilantro', 0, 6, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('sour cream thinned with 1/4 cup milk', 0.5, 3, 2);
INSERT INTO ingredient (description, amount, unit_of_measure_id, recipe_id) VALUES
('lime, cut into wedges', 1, 6, 2);

INSERT INTO recipe_category VALUES (2,1);
INSERT INTO recipe_category VALUES (2,3);
