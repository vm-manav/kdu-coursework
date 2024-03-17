import React, { useEffect, useState } from 'react'
import { IQuotes } from '../type/Quotes.types';
import { Quote } from './Quote';

export function QuotesApp() {

    const [ allQuotes,setAllQuotes ] = useState<IQuotes[]>([]);
    const [ quotes,setQuotes ] = useState<IQuotes[]>([]);
    const [ filter,setFilter ] = useState<string[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    useEffect(()=>{
        fetch("https://api.quotable.io/quotes/random?limit=3")
        .then((response)=>response.json())
        .then((data : IQuotes[])=>{
        return setAllQuotes(data);
        })
    },[]);

    useEffect(()=>{
        setQuotes(allQuotes);
    },[allQuotes]);

    const handleRemoveTag = (tagToRemove:string) => {
        const updatedFilter = filter.filter((tag) => tag !== tagToRemove);
        setFilter(updatedFilter);
      };

      useEffect(() => {
        if (filter.length === 0) {
          setQuotes(allQuotes);
        } else {
          const filteredQuotes = allQuotes.filter((quote) =>
            quote.tags.some((tag) => filter.includes(tag))
          );
          setQuotes(filteredQuotes);
        }
      }, [filter, allQuotes]);

      const handleNewQuoteClick = () => {
        fetch("https://api.quotable.io/quotes/random")
        setIsLoading(true);
        fetch("https://api.quotable.io/quotes/random")
        .then((response) => response.json())
        .then((data: IQuotes[]) => {
            setAllQuotes([data[0], ...allQuotes]);
            setIsLoading(false);
        })
        .catch((error) => {
            setIsLoading(false);
        });
      };

  return (
    <div id="quotes-app">
        <div className="add-quote">
            <button id="new-quote-button" onClick={handleNewQuoteClick} disabled={isLoading}>
                {isLoading ? <span>Loading...  <span id='spin'> </span></span> : <span>NEW QUOTE</span>}
            </button>
        </div>

        <p id='heading-filter'>Flters</p>
        <div className="quote-tag-container">
            {
                filter.map((tag)=>{
                    return <span key={tag} className="quote-tag-element">{tag}<button className='delete-tag-button' onClick={() => handleRemoveTag(tag)}>x</button></span>  
                })
            }
        </div>
        <hr id="line"/>
        <div className='quotes-container'>
            {
                quotes.map((quote)=>{
                return <Quote filter={filter} setFilter={setFilter} key={quote._id} quote={quote} />;
                })
            }
        </div>
    </div>
  )
}
