import { IQuotes } from "../type/Quotes.types";
import "../Css-files/QuotesApp.scss"
import "../Css-files/Quote.scss";

interface IQuotePropData{
    quote : IQuotes;
    setFilter: React.Dispatch<React.SetStateAction<string[]>>;
    filter : string[];
}

export function Quote( { quote,filter,setFilter } :Readonly<IQuotePropData>) {

  function handelTagFilterClick(tag :string) {
    const isTagPresent = filter.includes(tag);

    if (isTagPresent) {
      return;
    } else {
      const updatedFilter = [...filter, tag];
      setFilter(updatedFilter);
    }

  }


  return (
    <div id="quote-container">
      <p id="quote-content">{quote.content}</p>
      <p id="quote-author">{quote.author}</p>
      <p id="quote-date">{quote.dateAdded}</p>

      <div className="quote-tag-container">
        {
            quote.tags.map((tag)=>{
                return <button className="quote-tag-element" onClick={()=>handelTagFilterClick(tag)} key={tag}>{tag}</button>;
            })
        }
      </div>
    </div>
  )
}
