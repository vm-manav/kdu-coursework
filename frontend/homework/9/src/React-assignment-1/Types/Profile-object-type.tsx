export interface Iskills{
    id : number;
    skill : string;
}
export interface Ihobbies{
    id : number;
    hobby : string;
}

export interface IProfileObject{
    name : string,
    fullName : string;
    qualification : string;
    skills : Iskills[];
    hobbies : Ihobbies[];
}

