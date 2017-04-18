package ej;

public class Pascal {
	private int[][] almacen_pascal;
	
	public Pascal(int n){
		this.almacen_pascal = new int[n][n];
	}
	
	public void llenarTriangulo(){
		for(int i=0; i<this.almacen_pascal.length; i++){
			for(int j=0; j<=i; j++){
				if((j==0)||(j==i)){
					this.almacen_pascal[i][j]=1;
					System.out.print(1 +" ");
				}
				else{
					this.almacen_pascal[i][j]= this.almacen_pascal[i-1][j]+this.almacen_pascal[i-1][j-1];
					System.out.print(this.almacen_pascal[i][j] +" ");
				}
			}
			System.out.print("\n");
		}
	}
}
