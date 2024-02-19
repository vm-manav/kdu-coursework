import { Ihobbies } from '../Types/Profile-object-type'
import "../Css-files/Hobbies.scss";
interface IHobbiesPropData{
  hobbies : Ihobbies[];
}

export function Hobbies({hobbies} : Readonly<IHobbiesPropData>) {
  return (
    <div id='hobbies'>
        <div id='hobbies-heading'>Hobbies</div>
        <ul id='list-container'>
          {
              hobbies.map((item: Ihobbies)=>{
                  return (
                      <li className='list-element' key={item.id}>{item.hobby}</li>
                  )
              })
          }
        </ul>
    </div>
  )
}
