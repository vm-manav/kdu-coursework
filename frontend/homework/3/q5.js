function getAllKeys(obj) {
    const allKeys = [];

    function traverseObject(currentObj) {
        Object.keys(currentObj).forEach(key => {
            allKeys.push(key);
            if (typeof currentObj[key] === 'object' && currentObj[key] !== null) {
                traverseObject(currentObj[key]);
            }
        });
    }

    traverseObject(obj);
    return allKeys;
}

function getAllValues(obj) {
    const allValues = [];
    
    function traverseObject(currentObj) {
        Object.values(currentObj).forEach(value => {
            if (typeof value === 'object' && value !== null) {
                traverseObject(value);  
            } 
            else {
                allValues.push(value);
            }
        });
    }

    traverseObject(obj);
    return allValues;
}


const player = {
    firstName: "Leo",
    lastName: "Messi",
    address: {
        country: "Spain",
        city: "Barcelona",
    },
    careerInfo: {
        fcBarcelona: {
            appearances: 780,
            goals: {
                premierLeagueGoals: 590,
                championsLeagueGoals: 50,
            },
        },
    },
};

const keys = getAllKeys(player);
const values = getAllValues(player);

console.log("All Keys:", keys);
console.log("All Values:", values);