class Shoe {
    constructor(type, color, size, price) {
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}

class Shirt {
    constructor(type, color, size, price) {
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}

const shoe1 = new Shoe("Running Shoes","White","9",4000);
const shoe2 = new Shoe("Formal Shoes","Brown","9",3600);

const shirt1 = new Shirt("Casual Shirt","Black","S",2000);
const shirt2 = new Shirt("Frrmal Shirt","Purple","M",2500);
const shirt3 = new Shirt("Denim Shirt","Blue","L",3000);

const warehouse = [shoe1, shoe2, shirt1, shirt2, shirt3];

console.log("Warehouse :",warehouse);

let totalPrice = 0;
for (const product of warehouse) {
    totalPrice += product.price;
}

warehouse.sort((a,b)=>b.price-a.price);

const blueProducts = warehouse.filter(product=>product.color==="Blue");



console.log("Total Price of Products : ",totalPrice);
console.log("Warehouse Sorted by Price in Descending order : ",warehouse);
console.log("Blue Products in the Warehouse : ",blueProducts);
