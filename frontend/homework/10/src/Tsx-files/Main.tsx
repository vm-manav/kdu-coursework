import React, { useState } from 'react'
import { ListItems } from './ListItems'
import "../Css-files/Main.scss";
import { IAllStateData } from '../type/Items-list-type';

export function Main({ items , setItems , searchValue , setSearchValue , searchResult , setSearchResult}: Readonly<IAllStateData>) {

    const [ itemElement , setItemElement ]= useState("");

    function handelChangedItemElement(event:any){
        setItemElement(event.target.value);
    }
    function addItem(){
        if(itemElement.trim().length === 0 || itemElement.length === 0){
            console.log("empty item can not be added");
            return;
        }

        const newItem={
            id : items.length===0 ? 1 : (items.length)+1,
            item : itemElement
        }
        setItems([...items,newItem]);
        setItemElement('');
    }

  return (
    <div className='main-cont'>
      <div id='item-container'>
        <h2 className='main-cont-heading'>Add Items</h2>
        <div className='input-section'>
            <input id='input-box' type="text" onChange={handelChangedItemElement} value={itemElement} />
            <button id='submit-button' onClick={addItem}>Submit</button>
        </div>
      </div>
      <div id='list-container'>
        <h2 className='main-cont-heading'>Items</h2>
        <ul className='list'>
            {
              (searchResult.length>0)&&searchValue.length > 0 &&
                searchResult.map(item =>{
                   return  <ListItems key={item.id} listItem={item} items={searchResult} setItems={setSearchResult}/>
                })
            }
            {
              (searchValue.length>0) && (searchResult.length===0) &&
                <p>No Results Match</p>
            }
            {
              (searchValue.length===0)&&
                items.map(item =>{
                   return<ListItems key={item.id} listItem={item} items={items} setItems={setItems}/>
                })
            }
        </ul>
      </div>
    </div>
  )
}
