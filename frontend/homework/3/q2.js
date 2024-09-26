const array = ["Sunday   ","   Monday  ","  Tuesday","Wednesday  ","  Thursday   ","   Friday","Saturday    "];

const modifiedArray = array.map(day => 
    day.trim()
    .toUpperCase()
    .substring(0, 3));

console.log(modifiedArray);



function codeString(inputString) {
    return inputString.trim()
            .replace(/a/g,'4')
            .replace(/e/g,'3')
            .replace(/i/g,'1')
            .replace(/o/g,'0')
            .replace(/s/g,'5');
}


const input1 = "javascript is cool  ";
const input2 ="programming is fun";
const input3 ="  become a coder";
const output1 = codeString(input1);
const output2 = codeString(input2);
const output3 = codeString(input3);


console.log("Coded String 1 : ", output1);
console.log("Coded String 2 : ", output2);
console.log("Coded String 3 : ", output3);