var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var Recipes = /** @class */ (function () {
    function Recipes(image, name, rating, cuisine, ingredients, difficulty, caloriesPerServing) {
        this.timeTaken = 0;
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.difficulty = difficulty;
        this.caloriesPerServing = caloriesPerServing;
    }
    Recipes.prototype.addTimeTaken = function (prepTimeMinutes, cookTimeMinutes) {
        this.timeTaken = prepTimeMinutes + cookTimeMinutes;
    };
    return Recipes;
}());
function recipeModel() {
    var recipesarray = [];
    function printRecipiesStored() {
        console.log(recipesarray);
    }
    function printError() {
        console.log("Could not find recipies");
    }
    function fetchRecipesFromAPI() {
        return __awaiter(this, void 0, void 0, function () {
            var recipies, response;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0: return [4 /*yield*/, fetch("https://dummyjson.com/recipes")];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.json()];
                    case 2:
                        recipies = _a.sent();
                        recipies.recipes.forEach(function (r) {
                            var newRecipie = new Recipes(r.image, r.name, r.rating, r.cuisine, r.ingredients, r.difficulty, r.caloriesPerServing);
                            newRecipie.addTimeTaken(r.prepTimeMinutes, r.cookTimeMinutes);
                            recipesarray.push(newRecipie);
                        });
                        return [2 /*return*/];
                }
            });
        });
    }
    function searchRecipes(searchRecipie) {
        return __awaiter(this, void 0, void 0, function () {
            var url, recipies, response, newRecipie;
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        url = "https://dummyjson.com/recipes/search?q=".concat(searchRecipie);
                        return [4 /*yield*/, fetch(url)];
                    case 1:
                        response = _a.sent();
                        return [4 /*yield*/, response.json()];
                    case 2:
                        recipies = _a.sent();
                        newRecipie = new Recipes(recipies.recipes[0].image, recipies.recipes[0].name, recipies.recipes[0].rating, recipies.recipes[0].cuisine, recipies.recipes[0].ingredients, recipies.recipes[0].difficulty, recipies.recipes[0].caloriesPerServing);
                        newRecipie.addTimeTaken(recipies.recipes[0].prepTimeMinutes, recipies.recipes[0].cookTimeMinutes);
                        return [2 /*return*/, newRecipie];
                }
            });
        });
    }
    function run() {
        fetchRecipesFromAPI().then(function () {
            printRecipiesStored();
        }).catch(function () {
            printError();
        });
        searchRecipes("pizza").then(function (recipie) {
            console.log(recipie);
        }).catch(function () {
            printError();
        });
        printRecipiesStored();
    }
    run();
}
// recipeModel();
var recipesarray = [];
function searchRecipes(searchRecipie) {
    return __awaiter(this, void 0, void 0, function () {
        var url, recipies, response;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    url = "https://dummyjson.com/recipes/search?q=".concat(searchRecipie);
                    return [4 /*yield*/, fetch(url)];
                case 1:
                    response = _a.sent();
                    return [4 /*yield*/, response.json()];
                case 2:
                    recipies = _a.sent();
                    recipies.recipes.forEach(function (r) {
                        var newRecipie = new Recipes(r.image, r.name, r.rating, r.cuisine, r.ingredients, r.difficulty, r.caloriesPerServing);
                        newRecipie.addTimeTaken(r.prepTimeMinutes, r.cookTimeMinutes);
                        recipesarray.push(newRecipie);
                    });
                    return [2 /*return*/];
            }
        });
    });
}
function addRecipeToHTML(recipeObject) {
    var section = document.querySelector(".all-recipies");
    if (section === null) {
        return;
    }
    var recipeDiv = document.createElement("div");
    recipeDiv.className = "recipie";
    var img = document.createElement("img");
    img.className = "img-recipe";
    img.src = recipeObject.image;
    img.alt = "Food image";
    recipeDiv.appendChild(img);
    var dishParagraph = document.createElement("p");
    dishParagraph.className = "dish";
    dishParagraph.textContent = recipeObject.name;
    recipeDiv.appendChild(dishParagraph);
    var ingredientsDiv = document.createElement("div");
    ingredientsDiv.className = "ingredients";
    recipeObject.ingredients.forEach(function (ingredient) {
        var ingredientParagraph = document.createElement("p");
        ingredientParagraph.textContent = ingredient;
        ingredientsDiv.appendChild(ingredientParagraph);
    });
    recipeDiv.appendChild(ingredientsDiv);
    var block1Div = document.createElement("div");
    block1Div.className = "block1";
    var cuisineParagraph = document.createElement("p");
    cuisineParagraph.textContent = recipeObject.cuisine;
    var difficultyParagraph = document.createElement("p");
    difficultyParagraph.textContent = recipeObject.difficulty;
    block1Div.appendChild(cuisineParagraph);
    block1Div.appendChild(difficultyParagraph);
    recipeDiv.appendChild(block1Div);
    var block2Div = document.createElement("div");
    block2Div.className = "block2";
    var timeTakenParagraph = document.createElement("p");
    timeTakenParagraph.textContent = recipeObject.timeTaken + " minutes";
    var caloriesParagraph = document.createElement("p");
    caloriesParagraph.textContent = recipeObject.caloriesPerServing.toString();
    block2Div.appendChild(timeTakenParagraph);
    block2Div.appendChild(caloriesParagraph);
    recipeDiv.appendChild(block2Div);
    var ratingDiv = document.createElement("div");
    ratingDiv.className = "rating";
    var ratingParagraph = document.createElement("p");
    ratingParagraph.textContent = recipeObject.rating.toString();
    ratingDiv.appendChild(ratingParagraph);
    recipeDiv.appendChild(ratingDiv);
    section.appendChild(recipeDiv);
}
var searchButton = document.getElementById("search-button");
var inputBox = document.getElementById("input-box");
searchButton === null || searchButton === void 0 ? void 0 : searchButton.addEventListener("click", function () {
    var search = inputBox === null || inputBox === void 0 ? void 0 : inputBox.value;
    console.log(inputBox);
    if (search === null) {
        return;
    }
    searchRecipes(search).then(function () {
        recipesarray.forEach(function (recipe) {
            addRecipeToHTML(recipe);
        });
    }).catch(function () {
        console.log("recipe not found");
    });
});
