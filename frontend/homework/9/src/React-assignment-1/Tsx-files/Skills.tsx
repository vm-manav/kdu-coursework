import { Iskills } from "../Types/Profile-object-type"
import "../Css-files/Skills.scss";
interface ISkillsPropData{
    skills : Iskills[]; 
}

export function Skills({skills} : Readonly<ISkillsPropData>) {
  return (
    <div id="skills">
        <div id="skills-heading">Skills</div>
        <ul id="list-container">
            {
                skills.map((item: Iskills)=>{
                    return (
                        <li className="list-element" key={item.id}>{item.skill}</li>
                    )
                })
            }
        </ul>
    </div>
  )
}
