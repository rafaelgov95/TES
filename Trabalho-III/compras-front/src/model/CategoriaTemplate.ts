
export default class CategoriaTempate {
    public id:number;
    public nome:string;
    constructor()
    constructor(id: number)
    constructor(id?: number,nome?:string) {
        this.id=0;
        this.nome="";
        
    }
    public toString = () : string => {
        return this.nome;
    }
}