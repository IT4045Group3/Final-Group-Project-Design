# Final-Group-Project-Design
Final/Group Project Design


##Intro
Introduction
Group 3 Recipe: Your Ultimate Culinary Companion

Welcome to Group 3 Recipe, your one-stop app for all your culinary adventures! Whether you're a seasoned chef or just starting in the kitchen,   here to elevate your cooking experience. With a vast collection of recipes, personalized recommendations, and a user-friendly interface, you'll embark on a delightful gastronomic journey like never before.

Key Features

User Registration: Create your account and access personalized content, save your favorite recipes, and track your cooking progress.

Browse Recipes: Explore a diverse range of recipes, from quick weeknight dinners to gourmet delights. Discover new dishes to cook and savor.

Personalized Dashboard: Once registered, access your personalized dashboard with tailored recipe recommendations based on your preferences and previous cooking history.

Search and Filter: Easily find recipes that match your dietary preferences, ingredients on hand, or cuisine cravings.

Cooking Progress Tracker: Keep track of your cooking progress for each recipe, making it easy to resume or plan your next culinary adventure.

Community and Sharing: Join a community of fellow food enthusiasts, share your culinary creations, and discover inspiration from others.

Storyboard
User Registration

Scenario: As a new user, I visit the Recipe  app.

Action: I should see an option to sign up with my email and create a password.
Scenario: I have registered.

Action: When I log in with my credentials, I should access my personalized dashboard.
Scenario: I have already registered.

Action: When I try to sign up with the same email, I should receive an error message indicating that the email is already in use.
Browsing Recipes

Scenario: As a user, I want to browse a variety of recipes.

Action: I can explore a rich collection of recipes organized by categories, cuisines, or dietary preferences.
Scenario: I'm in the mood for something specific.

Action: I can use the search and filter options to quickly find recipes that match my criteria.
Scenario: I want to try a new dish.

Action: I can discover new recipes, read reviews from other users, and decide which dish to cook next.
Project Group IIIis your culinary companion, designed to make your cooking experience enjoyable, convenient, and flavorful. Get ready to embark on a journey of delicious discoveries!









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

#### Interfaces:

- IRecipeDao: Represents a data access object for recipes. It provides methods for finding recipes by ID or name, creating, updating, and deleting recipes.
- IUserDao: Represents a data access object for users. It provides methods for finding users by ID or email, creating, updating, and deleting users.
- IRecipeCollectionDao: Represents a data access object for recipe collections. It provides methods for finding collections by ID or user ID, creating user collections, adding and removing recipes from collections, and deleting collections.

#### Classes:

- Ingredient: Represents an ingredient with attributes such as ingredientId and name.
- Recipe: Represents a recipe with attributes like recipeId, name, cuisine, type, difficulty, ingredients (a list of Ingredient objects), and instructions (a list of Instruction objects). It also has a method to view recipe details.
- RecipeList: Represents a list of recipes (recipes attribute is a list of Recipe objects). It provides methods for filtering recipes by type or difficulty and browsing the recipe list.
- Instruction: Represents an instruction with attributes like instructionId, stepNumber, description, and videoURL.
- RecipeCollection: Represents a collection of recipes with attributes such as collectionId, userId, and recipeIds (a list of recipe IDs). It has methods for adding, removing, and retrieving recipes from the collection.
- PersonalizedDashboard: Represents a personalized dashboard for a user with attributes userId, theme, and widgets (a list of Widget objects). It has a method to display the user's dashboard.
- User: Represents a user with attributes like userId, username, email, and password. It provides methods for user registration, login, and saving recipes.


## JSON Schema

```Json
{ 
   "title" : "cookingRecipe", 
   "type" : "object", 
   "properties" : { 
      "recipeId" : { 
         "description" : "The unique identifier of the recipe.", 
         "type" : "Integer" 
         }, 
      "recipeName" : { 
         "type" : "string" 
      }, 
      "cookTime" : { 
         "type" : "string" 
      } 
      "tags" : { 
         "description" : "Classification of dishes.", 
         "type" : "array", 
         "intems" : { 
            "type" : "string" 
         }, 
         "uniqueItems" : true 
      }, 
      "ingredients" : { 
         "type" : "array", 
         "item" : { 
            "type" : "string", 
            "type" : "number" 
         } 
      }, 
      "instructions" : { 
         "type" : "array", 
         "items" : { 
            "type" : "string" 
         } 
      } 
   } 
   "required" : ["recipeName", "cookTime", "ingredients", "instrucions"] 
}
```Json

## Scrume Roles

UI Specialist : Chang Xu
Business Logic/Persitence : Saif Bayyari
DevOps/Product Owner : Yifan Bian
Scrum Master : Kozimjon Kuchkorov
GitHub Admin : Haoran Wang

## Weekly Group Meeting Link

https://teams.microsoft.com/l/meetup-join/19%3ameeting_ODhlNzgyZTktMzcxZi00NmFlLWFjMjQtZjA2ZDQ2ZGRhM2E5%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%221d9c1ade-710f-4494-86de-fd494f2dab2b%22%7d
