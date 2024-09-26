let addbutton=document.getElementById("add-button");

addbutton.addEventListener("click",addItems);

function addItems(){
    let inputField=document.getElementById("input-box");
    const data=inputField.value;
    if(data.length==0){
        alert("empty text cannot be added");
        return;
    }
    let list=document.getElementById("list");
    let item=document.createElement("li");

    item.textContent=data;
    list.appendChild(item);

    let deletebutton=document.createElement("button");
    deletebutton.textContent = "Delete";
    deletebutton.addEventListener("click",()=>{
        list.removeChild(item);
    })
    
    deletebutton.classList.add('delete_button')
    item.appendChild(deletebutton);
    console.log(deletebutton)
}