interface Irecipe{
    id : number;
    name : string;
    ingredients : string[];
    instructions : string[];
    prepTimeMinutes : number;
    cookTimeMinutes : number;
    servings : number;
    difficulty : string;
    cuisine : string;
    caloriesPerServing : number;
    tags : string[];
    userId : number;
    image : string;
    rating : number;
    reviewCount : number;
    mealType : string[];
}

class Recipes{
    image : string;
    name : string;
    rating : number;
    cuisine : string;
    ingredients : string[];
    difficulty : string;
    timeTaken : number=0;
    caloriesPerServing :number;

    public constructor(image : string,name : string,
        rating : number,cuisine : string,
        ingredients : string[],difficulty : string,
        caloriesPerServing :number){
            this.image=image;
            this.name=name;
            this.rating=rating;
            this.cuisine=cuisine;
            this.ingredients=ingredients;
            this.difficulty=difficulty;
            this.caloriesPerServing=caloriesPerServing;
        }
    public addTimeTaken(prepTimeMinutes : number,cookTimeMinutes : number){
        this.timeTaken=prepTimeMinutes+cookTimeMinutes;
    }
}


function recipeModel():void{

    
    let recipesarray:Recipes[]=[];

    function printRecipiesStored(){
        console.log(recipesarray);   
    }
    function printError(){
        console.log("Could not find recipies");   
    }

    async function fetchRecipesFromAPI(){
        let recipies;
        const response = await fetch("https://dummyjson.com/recipes");
        recipies = await response.json();
        recipies.recipes.forEach((r: Irecipe)=>{
            const newRecipie=new Recipes(r.image,r.name,r.rating,r.cuisine,r.ingredients,r.difficulty,r.caloriesPerServing);
            newRecipie.addTimeTaken(r.prepTimeMinutes,r.cookTimeMinutes);
            recipesarray.push(newRecipie);
        })
    }

    async function searchRecipes(searchRecipie : string){ 

        const url=`https://dummyjson.com/recipes/search?q=${searchRecipie}`;

        let recipies;
        const response = await fetch(url);
        recipies = await response.json();
        const newRecipie=new Recipes(recipies.recipes[0].image,recipies.recipes[0].name,recipies.recipes[0].rating,recipies.recipes[0].cuisine,recipies.recipes[0].ingredients,recipies.recipes[0].difficulty,recipies.recipes[0].caloriesPerServing);
        newRecipie.addTimeTaken(recipies.recipes[0].prepTimeMinutes,recipies.recipes[0].cookTimeMinutes);
        return newRecipie;
    }

    function run(){
        fetchRecipesFromAPI().then(()=>{
            printRecipiesStored();
        }).catch(()=>{
            printError();
        })

        searchRecipes("pizza").then((recipie)=>{
            console.log(recipie);
        }).catch(()=>{
            printError();
        })

        printRecipiesStored();
    }

    run();
}

recipeModel();



let recipesarray:Recipes[]=[];
async function searchRecipes(searchRecipie : string | undefined){ 

    const url=`https://dummyjson.com/recipes/search?q=${searchRecipie}`;
    let recipies;
    const response = await fetch(url);
    recipies = await response.json();
    recipies.recipes.forEach((r: Irecipe)=>{
        const newRecipie=new Recipes(r.image,r.name,r.rating,r.cuisine,r.ingredients,r.difficulty,r.caloriesPerServing);
        newRecipie.addTimeTaken(r.prepTimeMinutes,r.cookTimeMinutes);
        recipesarray.push(newRecipie);
    })
}

function addRecipeToHTML(recipeObject:Recipes):void {
    const section = document.querySelector(".all-recipies");
    if(section===null){
        return;
    }

    let recipeDiv = document.createElement("div");
    recipeDiv.className = "recipie";

    let img = document.createElement("img");
    img.className = "img-recipe";
    img.src = recipeObject.image;
    img.alt = "Food image";
    recipeDiv.appendChild(img);

    let dishParagraph = document.createElement("p");
    dishParagraph.className = "dish";
    dishParagraph.textContent = recipeObject.name;
    recipeDiv.appendChild(dishParagraph);

    let ingredientsDiv = document.createElement("div");
    ingredientsDiv.className = "ingredients";
    recipeObject.ingredients.forEach((ingredient:string)=>{
        let ingredientParagraph = document.createElement("p");
        ingredientParagraph.textContent = ingredient;
        ingredientsDiv.appendChild(ingredientParagraph);
    });
    recipeDiv.appendChild(ingredientsDiv);

    let block1Div = document.createElement("div");
    block1Div.className = "block1";
    let cuisineParagraph = document.createElement("p");
    cuisineParagraph.textContent = recipeObject.cuisine;
    let difficultyParagraph = document.createElement("p");
    difficultyParagraph.textContent = recipeObject.difficulty;
    block1Div.appendChild(cuisineParagraph);
    block1Div.appendChild(difficultyParagraph);
    recipeDiv.appendChild(block1Div);

    let block2Div = document.createElement("div");
    block2Div.className = "block2";
    let timeTakenParagraph = document.createElement("p");
    timeTakenParagraph.textContent = recipeObject.timeTaken + " minutes";
    let caloriesParagraph = document.createElement("p");
    caloriesParagraph.textContent = recipeObject.caloriesPerServing.toString();
    block2Div.appendChild(timeTakenParagraph);
    block2Div.appendChild(caloriesParagraph);
    recipeDiv.appendChild(block2Div);

    let ratingDiv = document.createElement("div");
    ratingDiv.className = "rating";
    let ratingParagraph = document.createElement("p");
    ratingParagraph.textContent = recipeObject.rating.toString();
    ratingDiv.appendChild(ratingParagraph);
    recipeDiv.appendChild(ratingDiv);

    section.appendChild(recipeDiv);
}

let searchButton = document.getElementById("search-button");
let inputBox = document.getElementById("input-box");

searchButton?.addEventListener("click", ()=> {
    const search:string | null | undefined=inputBox?.nodeValue;
    console.log(inputBox);
    if(search===null){
        return;
    }
    searchRecipes(search).then(()=>{
        recipesarray.forEach((recipe)=>{
            addRecipeToHTML(recipe);
        })
    }).catch(()=>{
        console.log("recipe not found");
    })
});
