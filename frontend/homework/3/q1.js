const amount1=50;
const amount2=200;

const discount1=20;
const discount2=15;
const discount3=10;

function computeTip(payment){
    if(payment<amount1){
        return payment*(discount1/100);
    }
    else if(payment>=amount1 && payment<amount2) {
        return payment*(discount2/100);
    } 
    else if(payment >=amount2) {
        return payment*(discount3/100);
    }
    else{
        return 0;
    }
}

function computeFinalpayment(payment1,payment2,payment3){
    let tip1=computeTip(payment1);
    let tip2=computeTip(payment2);
    let tip3=computeTip(payment3);
    const tipsArray={tip1,tip2,tip3};

    let finalAmount1=payment1+tip1;
    let finalAmount2=payment2+tip2;
    let finalAmount3=payment3+tip3;
    const finalAmountsArray={finalAmount1,finalAmount2,finalAmount3};


    console.log(tipsArray);
    console.log(finalAmountsArray);
}


computeFinalpayment(140,45,200);
