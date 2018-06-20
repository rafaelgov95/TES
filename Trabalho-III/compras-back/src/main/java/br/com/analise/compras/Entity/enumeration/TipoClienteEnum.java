package br.com.analise.compras.Entity.enumeration;

public enum TipoClienteEnum {

   PESSOAFISICA("Pessoa Física"), PESSOAJURIDA("Pessoa Juridica");

   String descricao;

   private TipoClienteEnum(String descricao){
       this.descricao = descricao;
   }


    public String getTipoCliente(){
       return this.descricao;}

}
