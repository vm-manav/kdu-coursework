function modifyString(inputString) {
    const objectString=JSON.parse(inputString);
    for(const key in objectString) {
        if (key !=='email'&& typeof objectString[key] === 'string') {
            objectString[key]=objectString[key].toUpperCase();
        }
    }
    return objectString;
}

const inputString = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';
const outputString1 = modifyString(inputString);

console.log(outputString1);


function revertString(jsonString) {
    delete jsonString.email;
    return JSON.stringify(jsonString);

}

const outputString2=revertString(outputString1)
console.log("Output : "+outputString2);