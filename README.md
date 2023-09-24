# Final-Group-Project-Design
Final/Group Project Design




## Functional Requirements

 1: User Registration
As a User, I want to create an account
So that I can access personalized content and save my cooking progress.

**Given**: I am a new user.
**When**: I visit the app.
**Then**: I should see an option to sign up with my email and password.

**Given**: I have registered.
**When**: I log in with my credentials.
**Then**: I should access my personalized dashboard.

**Given**: I have already registered
**When**: I try to sign up with the same email
**Then**: I should receive an error message indicating that the email is already in use.


2: Browse Recipes
As a User, want to browse a variety of recipes
So that I can discover new dishes to cook.

**Given**: I am on the dashboard
**When**: I click on the "Browse Recipes" section
**Then**: I should see a list of available recipes.

**Given**: I want to filter recipes
**When**: I use the search or filter options
**Then**: I should be able to narrow down my recipe choices by cuisine, type, or difficulty level.

 3: View Recipe Details
As a User, I want to view detailed information about a recipe
So that I can decide if I want to try cooking it.

**Given**: I click on a specific recipe
**When**: I do so
**Then**: I should see a recipe page with the following details:
- Recipe name
- Cooking video tutorial
- List of ingredients
- Step-by-step cooking instructions

 4: Save and Favorite Recipes
As a User, I want to save and favorite recipes
So that I can easily access them later.

**Given**: I am logged in
**When**: I view a recipe
**Then**: I should have the option to save it to my recipe collection.

**Given**:I have saved recipes
**When**: I go to my profile
**Then**: I should see a list of my saved recipes and be able to mark them as favorites.


## Class Diagram

![Class Diagram](https://github.com/YifanBian-bianya/private/blob/master/Class%20diagram%20with%20UML%20notation.png)

## Class Diagram Description

- Ingredient: Represents an ingredient used in recipes. It has attributes ingredientId and name.
- Recipe: Represents a recipe in the application. It has attributes like recipeId, name, cuisine, type, difficulty, videoURL, and lists of ingredients and instructions. It also has a method viewRecipeDetails().
- RecipeList: Represents a list of recipes, created by a user. It contains a list of Recipe objects. It provides methods like filterByType(type: String) and filterByDifficulty(difficulty: String) to browse and filter recipes.
- Instruction: Represents step-by-step instructions for cooking a recipe. It has attributes instructionId, stepNumber, and description.
- RecipeCollection: Represents a collection of favored recipes for a user. It has attributes like collectionId, userId, and a list of recipe IDs. It provides methods to addToCollection(), removeFromCollection(), and getCollection().
- Personalized Dashboard: Represents a personalized dashboard for a user. It contains user-specific widgets and themes. It has attributes userId, theme, and a list of Widget objects. It can display the user's dashboard using the displayDashboard() method.
- User: Represents a user of the application. It has attributes like userId, username, email, and password. Users can register(), login(), and saveRecipe().


