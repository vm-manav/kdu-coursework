const socket=io("http://localhost:5001");


socket.on("connection-established", () => {
    let stockName=document.getElementById("stock-name");
    stockName.innerHTML="Zomato";
    let stockImage=document.getElementById("stock-image");
    stockImage.src="/logo.png";
});

socket.on('price-update', (data)=> {
    setPrice(data.price);
});

let prevPrice=0;
function setPrice(price){
    let currPrice=document.getElementById("current-stock-price");
    currPrice.innerHTML=price;
    let percentage=0;
    if(prevPrice==0){
        prevPrice=-price;
    } else{
        percentage=(((price-prevPrice)/prevPrice)*100).toFixed(2);
        prevPrice=price;
    }

    let percentageElement=document.getElementById("stock-growth-percentage");
    percentageElement.innerHTML=percentage+" %";    
}

const inputBox=document.getElementById("quantity-box");
const buyButton=document.getElementById("buy-button");
const sellButton=document.getElementById("sell-button");


buyButton.addEventListener("click",()=>{
    const quantity=parseInt(inputBox.value);

    if(!quantity || !Number.isInteger(quantity)  || quantity<=0) {
        console.log("Invalid quantity");
        return;
    }
    inputBox.value="";

    const datestamp=getCurrentDateTime();

    let priceBuy=prevPrice;
    if(priceBuy<0) {
        priceBuy=priceBuy*-1;
    }

    
    let historyDetailsContainer = document.querySelector('.history-details-container');

    let stockTransactionDetails = document.createElement('div');
    stockTransactionDetails.className = 'stock-transaction-details';

    let cont1 = document.createElement('div');
    cont1.className = 'cont1';

    let sdBlock1 = document.createElement('div');
    sdBlock1.className = 'sd-block1';
    sdBlock1.innerHTML = `<p class="traded-amount">${quantity}</p><p>Stocks</p>`;

    let sdBlock2 = document.createElement('div');
    sdBlock2.className = 'sd-block2';
    sdBlock2.innerHTML = `<p>Price : </p><p>${priceBuy}</p>`;

    let sdBlock3 = document.createElement('div');
    sdBlock3.className = 'sd-block3';
    sdBlock3.innerHTML = `<p>${datestamp}</p>`;

    let cont2 = document.createElement('div');
    cont2.className = 'cont2';
    cont2.innerHTML = '<p style="color: green;">BUY</p>';

    cont1.appendChild(sdBlock1);
    cont1.appendChild(sdBlock2);
    cont1.appendChild(sdBlock3);

    stockTransactionDetails.appendChild(cont1);
    stockTransactionDetails.appendChild(cont2);

    historyDetailsContainer.appendChild(stockTransactionDetails);
})


sellButton.addEventListener("click",()=>{
    const quantity=parseInt(inputBox.value);

    if(!quantity || !Number.isInteger(quantity)  || quantity<=0) {
        console.log("Invalid quantity");
        return;
    }
    inputBox.value="";

    const datestamp=getCurrentDateTime();

    let priceSell=prevPrice;
    if(priceSell<0) {
        priceSell=priceSell*-1;
    }

    
    let historyDetailsContainer = document.querySelector('.history-details-container');

    let stockTransactionDetails = document.createElement('div');
    stockTransactionDetails.className = 'stock-transaction-details';

    let cont1 = document.createElement('div');
    cont1.className = 'cont1';

    let sdBlock1 = document.createElement('div');
    sdBlock1.className = 'sd-block1';
    sdBlock1.innerHTML = `<p class="traded-amount">${quantity}</p><p>Stocks</p>`;

    let sdBlock2 = document.createElement('div');
    sdBlock2.className = 'sd-block2';
    sdBlock2.innerHTML = `<p>Price : </p><p>${priceSell}</p>`;

    let sdBlock3 = document.createElement('div');
    sdBlock3.className = 'sd-block3';
    sdBlock3.innerHTML = `<p>${datestamp}</p>`;

    let cont2 = document.createElement('div');
    cont2.className = 'cont2';
    cont2.innerHTML = '<p style="color: RED;">SELL</p>';

    cont1.appendChild(sdBlock1);
    cont1.appendChild(sdBlock2);
    cont1.appendChild(sdBlock3);

    stockTransactionDetails.appendChild(cont1);
    stockTransactionDetails.appendChild(cont2);

    historyDetailsContainer.appendChild(stockTransactionDetails);
})


function getCurrentDateTime() {
    let currentDate = new Date();
    let daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    let dayOfWeek = daysOfWeek[currentDate.getUTCDay()];
    let monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    let month = monthNames[currentDate.getUTCMonth()];
    let day = currentDate.getUTCDate();
    let year = currentDate.getUTCFullYear();
    let hours = currentDate.getUTCHours();
    let minutes = currentDate.getUTCMinutes();
    let seconds = currentDate.getUTCSeconds();
    let formattedDateTime = `${dayOfWeek}, ${day} ${month} ${year} ${hours}:${minutes}:${seconds} GMT`;
    return formattedDateTime;
}
