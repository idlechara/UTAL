package ej_2;

public class Pascal {
	private int[][] valores;
	
	public Pascal(int a){
		this.valores = new int[a][a];
	}
	
	public void llenarTriangulo(){
		for(int x=0; x<this.valores.length; x++){
			for(int y=0; y<=x; y++){
				if((y==0)||(y==x)){
					this.valores[x][y]=1;
					System.out.print(1 + "\t");
				}
				else{
					this.valores[x][y]= this.valores[x-1][y]+this.valores[x-1][y-1];
					System.out.print(this.valores[x][y] + "\t");
				}
			}
			System.out.println();
		}
	}
}
