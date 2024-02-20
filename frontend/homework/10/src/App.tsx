import { useState } from 'react';
import './App.scss';
import { IItemsObject } from './type/Items-list-type';
import { Header } from './Tsx-files/Header';
import { Main } from './Tsx-files/Main';

function App() {
  const itemsList : IItemsObject[]=[];

  const [items , setItems] = useState(itemsList);
  const [searchValue , setSearchValue] = useState("");
  const [searchResult , setSearchResult ] = useState<IItemsObject[]>([]);

  return (
    <div className="App">
      <Header items={items} setItems={setItems} searchValue={searchValue} 
          setSearchValue={setSearchValue} searchResult = {searchResult} setSearchResult={setSearchResult} />
      <Main items={items} setItems={setItems} searchValue={searchValue} 
          setSearchValue={setSearchValue} searchResult = {searchResult} setSearchResult={setSearchResult} />
    </div>
  );
} 

export default App;
