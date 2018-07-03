import Produto from "@/model/Produto";

export default class Categoria {
    public id:number;
    public nome:string;
    public produtos:Array<Produto>;

    constructor()
    constructor(id: number)
    constructor(id?: number,nome?:string,produtos?:Array<Produto>) {
        this.id=0;
        this.nome="";
        this.produtos= new Array();
    }
    public toString = () : string => {
        return this.nome;
    }
}