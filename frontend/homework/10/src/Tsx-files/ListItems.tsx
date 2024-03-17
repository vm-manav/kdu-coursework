import React from 'react'
import { IListItemState } from '../type/Items-list-type'
import "../Css-files/ListItem.scss";

export function ListItems({listItem , items ,setItems} : Readonly<IListItemState>) {

  function deleteItem(){
    setItems(items.filter((item)=>
      item.id!==listItem.id
    ));
  }

  return (
    <div className='individual-list-element'>
        <li className='list-item'>
            <span className='list-item-name'>{listItem.item}</span>
            <button onClick={deleteItem} className='delete-button'>X</button>
        </li>
    </div>
  )
}
