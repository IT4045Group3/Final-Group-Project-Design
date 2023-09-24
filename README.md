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

# Interfaces:

- IRecipeDao: Represents a data access object for recipes. It provides methods for finding recipes by ID or name, creating, updating, and deleting recipes.
- IUserDao: Represents a data access object for users. It provides methods for finding users by ID or email, creating, updating, and deleting users.
- IRecipeCollectionDao: Represents a data access object for recipe collections. It provides methods for finding collections by ID or user ID, creating user collections, adding and removing recipes from collections, and deleting collections.

# Classes:

- Ingredient: Represents an ingredient with attributes such as ingredientId and name.
- Recipe: Represents a recipe with attributes like recipeId, name, cuisine, type, difficulty, ingredients (a list of Ingredient objects), and instructions (a list of Instruction objects). It also has a method to view recipe details.
- RecipeList: Represents a list of recipes (recipes attribute is a list of Recipe objects). It provides methods for filtering recipes by type or difficulty and browsing the recipe list.
- Instruction: Represents an instruction with attributes like instructionId, stepNumber, description, and videoURL.
- RecipeCollection: Represents a collection of recipes with attributes such as collectionId, userId, and recipeIds (a list of recipe IDs). It has methods for adding, removing, and retrieving recipes from the collection.
- PersonalizedDashboard: Represents a personalized dashboard for a user with attributes userId, theme, and widgets (a list of Widget objects). It has a method to display the user's dashboard.
- User: Represents a user with attributes like userId, username, email, and password. It provides methods for user registration, login, and saving recipes.


