import "./App.scss";

import { UserInfo } from "./React-assignment-1/Tsx-files/UserInfo";
import { Skills } from "./React-assignment-1/Tsx-files/Skills";
import { Hobbies } from "./React-assignment-1/Tsx-files/Hobbies";
import { object } from "./React-assignment-1/Types/api-profile-data";

function App() {
  return (
    <div className="main-app-cont">
      <UserInfo profileObject={object} />
      <div className="details-cont">
        <Hobbies hobbies={object.hobbies} />
        <Skills skills={object.skills} />
      </div>
    </div>
  );
}

export default App;
