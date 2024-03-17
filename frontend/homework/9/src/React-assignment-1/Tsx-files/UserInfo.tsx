import { IProfileObject } from "../Types/Profile-object-type";
import "../Css-files/UserInfo.scss";
interface IProfilePropData{
    profileObject : IProfileObject;
}

export function UserInfo({profileObject} : Readonly<IProfilePropData>) {
  return (
    <div className="profile-container">
      <div className="profile-name">{profileObject.name}</div>
      <div className="profile-fullname">{profileObject.fullName}</div>
      <div className="profile-qualification">{profileObject.qualification}</div>
    </div>
  )
}
